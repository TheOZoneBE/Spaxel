package code.factories.components;

import code.components.ai.AIType;
import code.components.ai.BasicEnemyAIComponent;
import code.components.Component;

/**
 * Created by theo on 7/06/17.
 */
public class AIComponentFactory extends ComponentFactory {
    private AIType subType;

    public Component make(){
        switch(subType){
            case BASIC_ENEMY:
                return new BasicEnemyAIComponent();
            default:
                return null;
        }
    }

    public AIType getSubType() {
        return subType;
    }

    public void setSubType(AIType subType) {
        this.subType = subType;
    }
}
