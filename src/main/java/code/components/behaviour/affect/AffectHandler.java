package code.components.behaviour.affect;

import code.entity.Entity;

public abstract class AffectHandler {
    private AffectType type;

    public AffectHandler(AffectType type) {
        this.type = type;
    }

    public abstract void affect(Entity entity);

    /**
     * @return the type
     */
    public AffectType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(AffectType type) {
        this.type = type;
    }
}
