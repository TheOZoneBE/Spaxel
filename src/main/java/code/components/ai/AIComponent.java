package code.components.ai;

import code.components.Component;
import code.components.ComponentType;
import code.entity.Entity;

/**
 * Created by theo on 7/06/17.
 */
public abstract class AIComponent extends Component {
    private AIType aiType;

    public AIComponent(AIType subType) {
        super(ComponentType.AI);
        this.aiType = subType;
    }

    public abstract void execute(Entity entity);

    public AIType getAiType() {
        return aiType;
    }

    public void setAiType(AIType aiType) {
        this.aiType = aiType;
    }
}
