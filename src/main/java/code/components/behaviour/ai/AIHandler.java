package code.components.behaviour.ai;

import code.entity.Entity;

public abstract class AIHandler {
    private AIType type;

    public AIHandler(AIType type) {
        this.type = type;
    }

    public abstract void execute(Entity entity);

    /**
     * @return the type
     */
    public AIType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(AIType type) {
        this.type = type;
    }

}
