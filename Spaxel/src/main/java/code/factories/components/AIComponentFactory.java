package code.factories.components;

import code.components.ai.AIType;
import code.components.ai.BasicEnemyAIComponent;
import code.components.Component;
import code.components.ai.HomingMissileAIComponent;
import code.components.hit.HomingMissileHitComponent;

/**
 * Created by theo on 7/06/17.
 */
public class AIComponentFactory extends ComponentFactory {
    private AIType subType;

    public Component make(){
        switch(subType){
            case BASIC_ENEMY:
                return new BasicEnemyAIComponent();
            case HOMING_MISSILE:
                return new HomingMissileAIComponent();
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
