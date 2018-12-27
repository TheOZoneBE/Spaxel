package code.components.affect;

import code.components.ComponentType;
import code.components.move.MoveComponent;
import code.engine.NEntity;
import code.components.Component;

/**
 * Created by theod on 28-6-2017.
 */
public class SlowAffectComponent extends AffectComponent {
    public SlowAffectComponent(double factor) {
        super(AffectType.SLOW, factor);
    }

    public void affect(NEntity entity, NEntity victim) {
        MoveComponent mc = (MoveComponent) victim.getComponent(ComponentType.MOVE);
        mc.setAcc(mc.getAcc() * factor);
        mc.setMaxSpeed(mc.getMaxSpeed() * factor);
    }

    public Component copy() {
        return new SlowAffectComponent(factor);
    }
}
