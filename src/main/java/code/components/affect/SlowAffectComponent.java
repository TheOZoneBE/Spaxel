package code.components.affect;

import code.components.ComponentType;
import code.components.storage.move.MoveStorage;
import code.entity.Entity;
import code.components.Component;

/**
 * Created by theod on 28-6-2017.
 */
public class SlowAffectComponent extends AffectComponent {
    public SlowAffectComponent(double factor) {
        super(AffectType.SLOW, factor);
    }

    public void affect(Entity entity, Entity victim) {
        MoveStorage mc = (MoveStorage) victim.getComponent(ComponentType.MOVE);
        mc.setAcceleration(mc.getAcceleration() * factor);
        mc.setSpeed(mc.getSpeed() * factor);
    }

    public Component copy() {
        return new SlowAffectComponent(factor);
    }
}
