package code.components.ai;

import code.Constants;
import code.components.ComponentType;
import code.components.Component;
import code.components.link.LinkComponent;
import code.components.move.MoveComponent;
import code.components.position.PositionComponent;
import code.components.velocity.VelocityComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.math.VectorD;

import java.util.Set;

/**
 * Created by theo on 8/07/17.
 */
public class HomingMissileAIComponent extends AIComponent {
    private static final int DISTANCE_THRESHOLD = 1000;

    public HomingMissileAIComponent() {
        super(AIType.HOMING_MISSILE);
    }

    public void execute(NEntity entity) {
        PositionComponent pc = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        MoveComponent mc = (MoveComponent) entity.getComponent(ComponentType.MOVE);
        VelocityComponent vc = (VelocityComponent) entity.getComponent(ComponentType.VELOCITY);

        Set<NEntity> enemies = Engine.getEngine().getNEntityStream().getEntities(ComponentType.DAMAGE);

        double minDist = -1;
        NEntity closest = null;
        for (NEntity e : enemies) {
            if (e != ((LinkComponent) entity.getComponent(ComponentType.LINK)).getLink()) {
                PositionComponent epc = (PositionComponent) e.getComponent(ComponentType.POSITION);
                double dist = epc.getCoord().sum(pc.getCoord().multiplicate(-1)).length();
                if (minDist < 0 || dist < minDist) {
                    minDist = dist;
                    closest = e;
                }
            }
        }
        if (closest != null && minDist < DISTANCE_THRESHOLD) {
            PositionComponent cpc = (PositionComponent) closest.getComponent(ComponentType.POSITION);

            VectorD diff = cpc.getCoord().sum(pc.getCoord().multiplicate(-1));
            double rotToGet = Math.atan2(diff.getValue(0), diff.getValue(1));
            if (rotToGet < 0) {
                rotToGet += Constants.FULL_CIRCLE;
            }
            double rotChange = rotToGet - pc.getRot();
            if (Math.abs(rotChange) < mc.getTurnRate()) {
                vc.setDeltaRot(rotChange);
            } else if (rotChange < 0) {
                if (rotChange < -Math.PI) {
                    vc.setDeltaRot(mc.getTurnRate());
                } else {
                    vc.setDeltaRot(-mc.getTurnRate());
                }
            } else {
                if (rotChange > Math.PI) {
                    vc.setDeltaRot(-mc.getTurnRate());
                } else {
                    vc.setDeltaRot(mc.getTurnRate());
                }
            }

            vc.setVelocity(new VectorD(Math.sin(pc.getRot()), Math.cos(pc.getRot())).multiplicate(mc.getMaxSpeed()));
        }
    }

    public Component copy() {
        return new HomingMissileAIComponent();
    }
}
