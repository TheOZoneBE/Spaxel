package code.components.behaviour.death.effect;

import code.components.storage.status.StatusStorage;
import code.components.ComponentType;
import code.components.behaviour.death.DeathHandler;
import code.components.behaviour.death.DeathType;
import code.entity.Entity;

/**
 * Created by theod on 28-6-2017.
 */
public class DisableMoveAffectDeathComponent extends DeathHandler {
    public DisableMoveAffectDeathComponent() {
        super(DeathType.DISABLE_MOVE_AFFECT);
    }

    public void die(Entity entity) {
        Entity parent = entity.getParent();
        StatusStorage mc = (StatusStorage) parent.getComponent(ComponentType.STATUS);
        mc.setCanMove(true);
    }

}
