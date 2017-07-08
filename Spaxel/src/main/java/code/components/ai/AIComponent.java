package code.components.ai;

import code.components.Component;
import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.engine.NEntity;

/**
 * Created by theo on 7/06/17.
 */
public class AIComponent extends Component {
    private AIType aiType;

    public AIComponent(AIType subType) {
        super(ComponentType.AI);
        this.aiType = subType;
    }

    public void execute(NEntity entity){

    }

    public AIType getAiType() {
        return aiType;
    }

    public void setAiType(AIType aiType) {
        this.aiType = aiType;
    }
}
