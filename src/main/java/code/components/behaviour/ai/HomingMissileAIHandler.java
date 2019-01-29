package code.components.behaviour.ai;

import code.Constants;
import code.components.ComponentType;
import code.components.storage.move.MoveStorage;
import code.components.storage.transformation.TransformationStorage;
import code.components.storage.change.ChangeStorage;
import code.engine.Engine;
import code.entity.Entity;
import code.math.VectorD;
import code.util.EntityUtil;
import java.util.Set;

/**
 * Created by theo on 8/07/17.
 */
public class HomingMissileAIHandler extends AIHandler {
    private static final int DISTANCE_THRESHOLD = 1000;

    public HomingMissileAIHandler() {
        super(AIType.HOMING_MISSILE);
    }

    public void execute(Entity entity) {
        TransformationStorage pc =
                (TransformationStorage) entity.getComponent(ComponentType.TRANSFORMATION);
        MoveStorage mc = (MoveStorage) entity.getComponent(ComponentType.MOVE);
        ChangeStorage vc = (ChangeStorage) entity.getComponent(ComponentType.CHANGE);

        Set<Entity> enemies = Engine.get().getNEntityStream().getEntities(ComponentType.DAMAGE);

        double minDist = -1;
        Entity closest = null;
        for (Entity e : enemies) {
            if (e != entity.getParent()) {
                TransformationStorage epc =
                        (TransformationStorage) e.getComponent(ComponentType.TRANSFORMATION);
                double dist = epc.getPosition().sum(pc.getPosition().multiplicate(-1)).length();
                if (minDist < 0 || dist < minDist) {
                    minDist = dist;
                    closest = e;
                }
            }
        }
        if (closest != null && minDist < DISTANCE_THRESHOLD) {
            TransformationStorage cpc =
                    (TransformationStorage) closest.getComponent(ComponentType.TRANSFORMATION);

            VectorD diff = cpc.getPosition().sum(pc.getPosition().multiplicate(-1));
            double rotToGet = Math.atan2(diff.getValue(0), diff.getValue(1));
            if (rotToGet < 0) {
                rotToGet += Constants.FULL_CIRCLE;
            }
            double rotChange = rotToGet - pc.getRotation();
            vc.setRotationChange(EntityUtil.calculateDeltaRot(rotChange, mc.getTurnRate()));

            vc.setPositionChange(new VectorD(Math.sin(pc.getRotation()), Math.cos(pc.getRotation()))
                    .multiplicate(mc.getSpeed()));
        }
    }
}
