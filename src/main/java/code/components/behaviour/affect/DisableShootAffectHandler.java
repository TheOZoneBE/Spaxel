package code.components.behaviour.affect;

import code.components.storage.status.StatusStorage;
import code.components.ComponentType;
import code.entity.Entity;

/**
 * Created by theod on 28-6-2017.
 */
public class DisableShootAffectHandler extends AffectHandler {
    public DisableShootAffectHandler() {
        super(AffectType.DISABLE_SHOOT);
    }

    public void affect(Entity entity) {
        StatusStorage ac = (StatusStorage) entity.getParent().getComponent(ComponentType.STATUS);
        ac.setCanShoot(false);
    }
}
