package code.components.behaviour.affect;

import code.components.storage.status.StatusStorage;
import code.components.ComponentType;
import code.entity.Entity;

/**
 * Created by theod on 28-6-2017.
 */
public class DisableMoveAffectHandler extends AffectHandler {
    public DisableMoveAffectHandler() {
        super(AffectType.DISABLE_MOVE);
    }

    public void affect(Entity entity) {
        StatusStorage ac = (StatusStorage) entity.getParent().getComponent(ComponentType.STATUS);
        ac.setCanMove(false);
    }
}
