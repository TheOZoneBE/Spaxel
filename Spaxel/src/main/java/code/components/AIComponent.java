package code.components;

import code.engine.NEntity;

/**
 * Created by theo on 7/06/17.
 */
public class AIComponent extends Component{
    private AIType subType;

    public AIComponent(AIType subType) {
        super(ComponentType.AI);
        this.subType = subType;
    }

    public void execute(PositionComponent playerPos, NEntity entity){

    }

    public AIType getSubType() {
        return subType;
    }

    public void setSubType(AIType subType) {
        this.subType = subType;
    }
}
