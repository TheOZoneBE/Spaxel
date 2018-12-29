package code.factories.components;

import code.components.ai.AIType;
import code.components.ai.BasicEnemyAIComponent;
import code.components.Component;
import code.components.ai.DroppedItemAIComponent;
import code.components.ai.HomingMissileAIComponent;

/**
 * Created by theo on 7/06/17.
 */
public class AIComponentFactory extends ComponentFactory {
    private AIType subType;

    public AIComponentFactory() {
        super();
    }

    @Override
    public Component make() {
        Component result = null;
        switch (subType) {
        case BASIC_ENEMY:
            result = new BasicEnemyAIComponent();
            break;
        case HOMING_MISSILE:
            result = new HomingMissileAIComponent();
            break;
        case DROPPED_ITEM:
            result = new DroppedItemAIComponent();
            break;
        default:
            break;
        }
        return result;
    }

    public AIType getSubType() {
        return subType;
    }

    public void setSubType(AIType subType) {
        this.subType = subType;
    }
}
