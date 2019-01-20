package code.components.affect;

import code.components.storage.status.StatusStorage;
import code.components.Component;
import code.components.ComponentType;
import code.entity.Entity;

/**
 * Created by theod on 28-6-2017.
 */
public class DisableShootAffectComponent extends AffectComponent {
    public DisableShootAffectComponent() {
        super(AffectType.DISABLE_SHOOT, 0);
    }

    public void affect(Entity entity, Entity victim) {
        StatusStorage ac = (StatusStorage) victim.getComponent(ComponentType.STATUS);
        ac.setCanShoot(false);
    }

    public Component copy() {
        return new DisableShootAffectComponent();
    }
}
