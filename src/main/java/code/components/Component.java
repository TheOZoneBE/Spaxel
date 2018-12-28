package code.components;

import code.engine.NEntity;

/**
 * Created by theo on 31/05/17.
 */
public abstract class Component {
    private ComponentType type;

    public Component(ComponentType type) {
        this.type = type;
    }

    public abstract Component copy();

    public void addCascade(NEntity entity) {

    }

    public void removeCascade() {

    }

    public ComponentType getType() {
        return type;
    }
}
