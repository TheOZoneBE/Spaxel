package code.components.ai;

import code.components.ComponentType;
import code.components.link.LinkComponent;
import code.components.move.MoveComponent;
import code.components.position.PositionComponent;
import code.components.velocity.VelocityComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.math.VectorF;

import java.util.Set;

/**
 * Created by theo on 8/07/17.
 */
public class HomingMissileAIComponent extends AIComponent {
    public HomingMissileAIComponent() {
        super(AIType.HOMING_MISSILE);
    }

    public void execute(NEntity entity){
        PositionComponent pc = (PositionComponent)entity.getComponent(ComponentType.POSITION);
        MoveComponent mc = (MoveComponent)entity.getComponent(ComponentType.MOVE);
        VelocityComponent vc = (VelocityComponent)entity.getComponent(ComponentType.VELOCITY);

        Set<NEntity> enemies = Engine.getEngine().getNEntityStream().getEntities(ComponentType.DAMAGE);

        float minDist = 0;
        NEntity closest = null;
        for(NEntity e: enemies){
            if (e != ((LinkComponent)entity.getComponent(ComponentType.LINK)).getLink()){
                PositionComponent epc = (PositionComponent)e.getComponent(ComponentType.POSITION);
                float dis = epc.getCoord().sum(pc.getCoord().multiplicate(-1)).length();
                if (minDist == 0 || dis < minDist){
                    minDist = dis;
                    closest = e;
                }
            }
        }
        //TODO figure out bug with mindist not accurate: maybe calculation or maybe not correct position set in entities
        if (closest != null && minDist < 1000) {
            PositionComponent cpc = (PositionComponent) closest.getComponent(ComponentType.POSITION);

            VectorF diff = cpc.getCoord().sum(pc.getCoord().multiplicate(-1));
            float rotToGet = (float) (Math.atan2(diff.getValue(0), diff.getValue(1)));
            if (rotToGet < 0) {
                rotToGet += 2 * Math.PI;
            }
            float rotChange = rotToGet - pc.getRot();
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


            vc.setVelocity(new VectorF((float)Math.sin(pc.getRot()), (float)Math.cos(pc.getRot())).multiplicate(mc.getMaxSpeed()));
        }
    }
}
