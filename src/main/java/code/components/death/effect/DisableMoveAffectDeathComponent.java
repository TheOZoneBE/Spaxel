package code.components.death.effect;

import code.components.actor.ActorComponent;
import code.components.ComponentType;
import code.components.Component;
import code.components.death.DeathComponent;
import code.components.death.DeathType;
import code.components.effect.EffectComponent;
import code.components.link.LinkComponent;
import code.entity.Entity;

/**
 * Created by theod on 28-6-2017.
 */
public class DisableMoveAffectDeathComponent extends DeathComponent {
    public DisableMoveAffectDeathComponent() {
        super(DeathType.DISABLE_MOVE_AFFECT);
    }

    public void die(Entity entity) {
        Entity parent = ((LinkComponent) entity.getComponent(ComponentType.LINK)).getLink();
        ActorComponent mc = (ActorComponent) parent.getComponent(ComponentType.ACTOR);
        mc.setCanMove(true);
        EffectComponent ec = (EffectComponent) parent.getComponent(ComponentType.EFFECT);
        ec.getEffects().remove(entity);
    }

    public Component copy() {
        return new DisableMoveAffectDeathComponent();
    }
}
