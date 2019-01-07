package code.components.affect;

import code.components.actor.ActorComponent;
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
        ActorComponent ac = (ActorComponent) victim.getComponent(ComponentType.ACTOR);
        ac.setCanMove(false);
    }

    public Component copy() {
        return new DisableMoveAffectComponent();
    }

}
