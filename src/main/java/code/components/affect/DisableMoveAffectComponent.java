package code.components.affect;

import code.components.storage.status.StatusStorage;
import code.components.ComponentType;
import code.components.Component;
import code.entity.Entity;

/**
 * Created by theod on 28-6-2017.
 */
public class DisableMoveAffectComponent extends AffectComponent {
    public DisableMoveAffectComponent() {
        super(AffectType.DISABLE_MOVE, 0);
    }

    public void affect(Entity entity, Entity victim) {
        StatusStorage ac = (StatusStorage) victim.getComponent(ComponentType.STATUS);
        ac.setCanMove(false);
    }

    public Component copy() {
        return new DisableMoveAffectComponent();
    }

}
