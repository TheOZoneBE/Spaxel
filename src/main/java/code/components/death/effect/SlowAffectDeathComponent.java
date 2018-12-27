package code.components.death.effect;

import code.components.Component;
import code.components.ComponentType;
import code.components.affect.AffectComponent;
import code.components.death.DeathComponent;
import code.components.death.DeathType;
import code.components.effect.EffectComponent;
import code.components.link.LinkComponent;
import code.components.move.MoveComponent;
import code.engine.NEntity;

/**
 * Created by theod on 28-6-2017.
 */
public class SlowAffectDeathComponent extends DeathComponent {
    public SlowAffectDeathComponent() {
        super(DeathType.SLOW_AFFECT);
    }

    public void die(NEntity entity) {
        AffectComponent ac = (AffectComponent) entity.getComponent(ComponentType.AFFECT);
        NEntity parent = ((LinkComponent) entity.getComponent(ComponentType.LINK)).getLink();
        MoveComponent mc = (MoveComponent) parent.getComponent(ComponentType.MOVE);
        mc.setAcc(mc.getAcc() / ac.getFactor());
        mc.setMaxSpeed(mc.getMaxSpeed() / ac.getFactor());
        EffectComponent ec = (EffectComponent) parent.getComponent(ComponentType.EFFECT);
        ec.getEffects().remove(entity);
    }

    public Component copy(){
        return new SlowAffectDeathComponent();
    }
}
