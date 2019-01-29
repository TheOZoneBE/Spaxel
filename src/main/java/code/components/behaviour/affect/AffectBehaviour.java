package code.components.behaviour.affect;

import code.components.Behaviour;
import code.components.ComponentType;
import code.entity.Entity;

/**
 * Created by theod on 28-6-2017.
 */
public class AffectBehaviour extends Behaviour {
    private AffectHandler handler;

    public AffectBehaviour() {
        super(ComponentType.AFFECT);
    }

    public AffectBehaviour(AffectHandler handler) {
        super(ComponentType.AFFECT);
        this.handler = handler;
    }

    public void execute(Entity entity) {
        handler.affect(entity);
    }

    public AffectBehaviour copy() {
        return new AffectBehaviour(handler);
    }
}
