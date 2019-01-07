package code.engine;

import code.components.Component;
import code.components.ComponentType;
import java.util.EnumMap;
import java.util.Map;

/**
 * Represent an entity in the game
 * 
 * Created by theo on 31/05/17.
 */
public class NEntity {
    private EntityType type;
    private Map<ComponentType, Component> components;

    /**
     * Create a new entity of the given type
     * 
     * @param type the type of the entity
     */
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

    /**
     * Get the component of the given type from this entity
     * 
     * @param type the type of the component to get
     * 
     * @return the component
     */
    public Component getComponent(ComponentType type) {
        return components.get(type);
    }

    /**
     * Add a component to this entity
     * 
     * @param component the component to add
     */
    public void addComponent(Component component) {
        component.addCascade(this);
        components.put(component.getType(), component);
        Engine.get().getNEntityStream().addComponent(component.getType(), this);
    }

    /**
     * Remove a component of the given type from this entity
     * 
     * @param type the type of component to remove
     */
    public void removeComponent(ComponentType type) {
        components.get(type).removeCascade();
        components.remove(type);
        Engine.get().getNEntityStream().removeComponent(type, this);
    }

    /**
     * Call the cascading add method for all the component such that referenced entities are also
     * added in the entitystream
     */
    public void addCascade() {
        for (Component c : components.values()) {
            c.addCascade(this);
        }
    }

    /**
     * Call the cascading remove method for all the component such that referenced entities are also
     * removed in the entitystream
     */
    public void removeCascade() {
        for (Component c : components.values()) {
            c.removeCascade();
        }
    }

    /**
     * Copy the entity
     * 
     * @return a copy of this entity
     */
    public NEntity copy() {
        // TODO figure out copy implementation
        return new NEntity(type);
    }

}
