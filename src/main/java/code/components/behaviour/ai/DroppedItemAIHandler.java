package code.components.behaviour.ai;

import code.components.ComponentType;
import code.components.storage.change.ChangeStorage;
import code.entity.Entity;

/**
 * Created by theod on 11-7-2017.
 */
public class DroppedItemAIHandler extends AIHandler {
    private static final double HALF = 0.5D;

    public DroppedItemAIHandler() {
        super(AIType.DROPPED_ITEM);
    }

    public void execute(Entity entity) {
        ChangeStorage vc = (ChangeStorage) entity.getComponent(ComponentType.CHANGE);
        vc.setPositionChange(vc.getPositionChange().multiplicate(HALF));
    }
}
