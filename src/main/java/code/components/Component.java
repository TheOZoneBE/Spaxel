package code.components;

import code.entity.Entity;

/**
 * Created by theo on 31/05/17.
 */
public abstract class Component {
    private ComponentType type;

    /**
     * Create a new component of the specified type
     * 
     * @param type the type of the component
     */
    public Component(ComponentType type) {
        this.type = type;
    }

    /**
     * Return a copy of this component.
     * 
     * @return The copied component
     */
    public abstract Component copy();

    /**
     * Add an Entity in a cascading way
     * 
     * @param entity the cascading entity
     */
    public void addCascade(Entity entity) {

    }

    /**
     * Remove the cascading entity
     */
    public void removeCascade() {

    }

    public ComponentType getType() {
        return type;
    }
}
