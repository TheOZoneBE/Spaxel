package code.engine;

import code.components.Component;
import code.components.ComponentType;
import code.entity.Entity;

import java.util.*;

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

    public NEntityStream(){
        entityTypeMap = new EnumMap<>(EntityType.class);
        toAddEntityTypeMap = new EnumMap<>(EntityType.class);
        toRemoveEntityTypeMap = new EnumMap<>(EntityType.class);
        for (EntityType eType : EntityType.values()){
            entityTypeMap.put(eType, new HashSet<>());
            toAddEntityTypeMap.put(eType, new HashSet<>());
            toRemoveEntityTypeMap.put(eType, new HashSet<>());
        }
        componentTypeMap = new EnumMap<>(ComponentType.class);
        toAddComponentTypeMap = new EnumMap<>(ComponentType.class);
        toRemoveComponentTypeMap = new EnumMap<>(ComponentType.class);
        for (ComponentType cType : ComponentType.values()){
            componentTypeMap.put(cType, new HashSet<>());
            toAddComponentTypeMap.put(cType, new HashSet<>());
            toRemoveComponentTypeMap.put(cType, new HashSet<>());
        }
    }

    public Set<NEntity> getEntities(EntityType type){
        return entityTypeMap.get(type);
    }

    public Set<NEntity> getEntities(ComponentType type){
        return componentTypeMap.get(type);
    }

    public void addEntity(NEntity entity){
        toAddEntityTypeMap.get(entity.getType()).add(entity);
    }

    public void addEntities(EntityType type, List<NEntity> entities){
        toAddEntityTypeMap.get(type).addAll(entities);
    }

    public void removeEntity(NEntity entity){
        toRemoveEntityTypeMap.get(entity.getType()).add(entity);
    }

    public void addComponent(ComponentType type, NEntity entity){
        toAddComponentTypeMap.get(type).add(entity);
    }

    public void removeComponent(ComponentType type, NEntity entity){
        toRemoveComponentTypeMap.get(type).add(entity);
    }

    public void cleanup(){
        //add entities
        for(EntityType type: entityTypeMap.keySet()){
            entityTypeMap.get(type).addAll(toAddEntityTypeMap.get(type));
            for (NEntity e: toAddEntityTypeMap.get(type)){
                for (Component c: e.getComponents().values()){
                    toAddComponentTypeMap.get(c.getType()).add(e);
                }
            }
        }

        //remove entities
        for(EntityType type: entityTypeMap.keySet()){
            entityTypeMap.get(type).removeAll(toRemoveEntityTypeMap.get(type));
            for (NEntity e: toRemoveEntityTypeMap.get(type)){
                for (Component c: e.getComponents().values()){
                    toRemoveComponentTypeMap.get(c.getType()).add(e);
                }
            }
        }

        //add components
        for (ComponentType type: componentTypeMap.keySet()){
            componentTypeMap.get(type).addAll(toAddComponentTypeMap.get(type));
            toAddComponentTypeMap.get(type).clear();
        }

        //remove components
        for (ComponentType type: componentTypeMap.keySet()){
            componentTypeMap.get(type).removeAll(toRemoveComponentTypeMap.get(type));
            toRemoveComponentTypeMap.get(type).clear();
        }
    }

    public void clear(){
        for (EntityType type: EntityType.values()){
            entityTypeMap.get(type).clear();
            toAddEntityTypeMap.get(type).clear();
            toRemoveEntityTypeMap.get(type).clear();
        }
        for (ComponentType type: ComponentType.values()){
            componentTypeMap.get(type).clear();
            toAddComponentTypeMap.get(type).clear();
            toRemoveComponentTypeMap.get(type).clear();
        }
    }
}
