package code.components.ai;

import code.Constants;
import code.components.ComponentType;
import code.components.Component;
import code.components.link.LinkComponent;
import code.components.move.MoveComponent;
import code.components.position.PositionComponent;
import code.components.velocity.VelocityComponent;
import code.engine.Engine;
import code.entity.Entity;
import code.math.VectorD;
import code.util.EntityUtil;
import java.util.Set;

/**
 * Created by theo on 8/07/17.
 */
public class HomingMissileAIComponent extends AIComponent {
    private static final int DISTANCE_THRESHOLD = 1000;

    public HomingMissileAIComponent() {
        super(AIType.HOMING_MISSILE);
    }

    public void execute(Entity entity) {
        PositionComponent pc = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        MoveComponent mc = (MoveComponent) entity.getComponent(ComponentType.MOVE);
        VelocityComponent vc = (VelocityComponent) entity.getComponent(ComponentType.VELOCITY);

        Set<Entity> enemies = Engine.get().getNEntityStream().getEntities(ComponentType.DAMAGE);

        double minDist = -1;
        Entity closest = null;
        for (Entity e : enemies) {
            if (e != entity.getParent()) {
                PositionComponent epc = (PositionComponent) e.getComponent(ComponentType.POSITION);
                double dist = epc.getCoord().sum(pc.getCoord().multiplicate(-1)).length();
                if (minDist < 0 || dist < minDist) {
                    minDist = dist;
                    closest = e;
                }
            }
        }
        if (closest != null && minDist < DISTANCE_THRESHOLD) {
            PositionComponent cpc =
                    (PositionComponent) closest.getComponent(ComponentType.POSITION);

            VectorD diff = cpc.getCoord().sum(pc.getCoord().multiplicate(-1));
            double rotToGet = Math.atan2(diff.getValue(0), diff.getValue(1));
            if (rotToGet < 0) {
                rotToGet += Constants.FULL_CIRCLE;
            }
            double rotChange = rotToGet - pc.getRot();
            vc.setDeltaRot(EntityUtil.calculateDeltaRot(rotChange, mc.getTurnRate()));

            vc.setVelocity(new VectorD(Math.sin(pc.getRot()), Math.cos(pc.getRot()))
                    .multiplicate(mc.getMaxSpeed()));
        }
    }

    public Component copy() {
        return new HomingMissileAIComponent();
    }
}
