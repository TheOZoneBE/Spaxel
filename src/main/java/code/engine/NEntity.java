package code.engine;

import code.components.Component;
import code.components.ComponentType;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by theo on 31/05/17.
 */
public class NEntity {
    private EntityType type;
    private Map<ComponentType, Component> components;

    public NEntity(EntityType type) {
        this.type = type;
        this.components = new EnumMap<>(ComponentType.class);
    }

    public EntityType getType() {
        return type;
    }

    public Map<ComponentType, Component> getComponents() {
        return components;
    }

    public void setComponents(Map<ComponentType, Component> components) {
        this.components = components;
    }

    public Component getComponent(ComponentType type) {
        return components.get(type);
    }

    public void addComponent(Component component) {
        component.addCascade(this);
        components.put(component.getType(), component);
        Engine.get().getNEntityStream().addComponent(component.getType(), this);
    }

    public void removeComponent(ComponentType type) {
        components.get(type).removeCascade();
        components.remove(type);
        Engine.get().getNEntityStream().removeComponent(type, this);
    }

    public void addCascade() {
        for (Component c : components.values()) {
            c.addCascade(this);
        }
    }

    public void removeCascade() {
        for (Component c : components.values()) {
            c.removeCascade();
        }
    }

    public NEntity copy() {
        // TODO figure out copy implementation
        return new NEntity(type);
    }

}
