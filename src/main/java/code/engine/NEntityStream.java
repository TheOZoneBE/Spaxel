package code.engine;

import code.components.Component;
import code.components.ComponentType;

import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by theo on 31/05/17.
 */
public class NEntityStream {
    private EnumMap<EntityType, Set<NEntity>> entityTypeMap;
    private EnumMap<EntityType, Set<NEntity>> toAddEntityTypeMap;
    private EnumMap<EntityType, Set<NEntity>> toRemoveEntityTypeMap;
    private EnumMap<ComponentType, Set<NEntity>> componentTypeMap;
    private EnumMap<ComponentType, Set<NEntity>> toAddComponentTypeMap;
    private EnumMap<ComponentType, Set<NEntity>> toRemoveComponentTypeMap;
    private boolean clear;

    public NEntityStream() {
        entityTypeMap = new EnumMap<>(EntityType.class);
        toAddEntityTypeMap = new EnumMap<>(EntityType.class);
        toRemoveEntityTypeMap = new EnumMap<>(EntityType.class);
        for (EntityType eType : EntityType.values()) {
            entityTypeMap.put(eType, new HashSet<>());
            toAddEntityTypeMap.put(eType, new HashSet<>());
            toRemoveEntityTypeMap.put(eType, new HashSet<>());
        }
        componentTypeMap = new EnumMap<>(ComponentType.class);
        toAddComponentTypeMap = new EnumMap<>(ComponentType.class);
        toRemoveComponentTypeMap = new EnumMap<>(ComponentType.class);
        for (ComponentType cType : ComponentType.values()) {
            componentTypeMap.put(cType, new HashSet<>());
            toAddComponentTypeMap.put(cType, new HashSet<>());
            toRemoveComponentTypeMap.put(cType, new HashSet<>());
        }
    }

    public Set<NEntity> getEntities(EntityType type) {
        return entityTypeMap.get(type);
    }

    public Set<NEntity> getEntities(ComponentType type) {
        return componentTypeMap.get(type);
    }

    public NEntity getPlayer() {
        return entityTypeMap.get(EntityType.PLAYER).iterator().next();
    }

    public void addEntity(NEntity entity) {
        entity.addCascade();
        toAddEntityTypeMap.get(entity.getType()).add(entity);
    }

    public void addEntities(EntityType type, List<NEntity> entities) {
        for (NEntity e : entities) {
            e.addCascade();
        }
        toAddEntityTypeMap.get(type).addAll(entities);
    }

    public void removeEntity(NEntity entity) {
        entity.removeCascade();
        toRemoveEntityTypeMap.get(entity.getType()).add(entity);
    }

    public void addComponent(ComponentType type, NEntity entity) {
        toAddComponentTypeMap.get(type).add(entity);
    }

    public void removeComponent(ComponentType type, NEntity entity) {
        toRemoveComponentTypeMap.get(type).add(entity);
    }

    public void cleanup() {
        if (!clear) {
            // add entities
            addEntities();

            // remove entities
            removeEntities();

            // add and remove components
            for (Map.Entry<ComponentType, Set<NEntity>> entry : componentTypeMap.entrySet()) {
                ComponentType type = entry.getKey();
                Set<NEntity> entities = entry.getValue();
                entities.addAll(toAddComponentTypeMap.get(type));
                toAddComponentTypeMap.get(type).clear();
                entities.removeAll(toRemoveComponentTypeMap.get(type));
                toRemoveComponentTypeMap.get(type).clear();
            }
        } else {
            clearEntities();
            clear = false;
        }
    }

    private void addEntities() {
        for (Map.Entry<EntityType, Set<NEntity>> entry : entityTypeMap.entrySet()) {
            EntityType type = entry.getKey();
            entry.getValue().addAll(toAddEntityTypeMap.get(type));
            for (NEntity e : toAddEntityTypeMap.get(type)) {
                for (Component c : e.getComponents().values()) {
                    toAddComponentTypeMap.get(c.getType()).add(e);
                }
            }
            toAddEntityTypeMap.get(type).clear();
        }
    }

    private void removeEntities() {
        for (Map.Entry<EntityType, Set<NEntity>> entry : entityTypeMap.entrySet()) {
            EntityType type = entry.getKey();
            entry.getValue().removeAll(toRemoveEntityTypeMap.get(type));
            for (NEntity e : toRemoveEntityTypeMap.get(type)) {
                for (Component c : e.getComponents().values()) {
                    toRemoveComponentTypeMap.get(c.getType()).add(e);
                }
            }
            toRemoveEntityTypeMap.get(type).clear();
        }
    }

    public void scheduleClear() {
        clear = true;
    }

    private void clearEntities() {
        for (EntityType type : EntityType.values()) {
            entityTypeMap.get(type).clear();
            toAddEntityTypeMap.get(type).clear();
            toRemoveEntityTypeMap.get(type).clear();
        }
        for (ComponentType type : ComponentType.values()) {
            componentTypeMap.get(type).clear();
            toAddComponentTypeMap.get(type).clear();
            toRemoveComponentTypeMap.get(type).clear();
        }
    }
}
