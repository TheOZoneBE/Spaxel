package code.system;

import code.collision.HitShape;
import code.components.ComponentType;
import code.components.collision.CollisionComponent;
import code.components.hit.HitComponent;
import code.components.link.LinkComponent;
import code.components.position.PositionComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.engine.SystemType;
import code.math.MatrixD;
import code.util.MatrixUtil;

import java.util.Set;

/**
 * Created by theo on 20/06/17.
 */
public class HitSystem extends GameSystem {
    public HitSystem() {
        super(SystemType.HIT);
    }

    public void update() {
        Set<NEntity> entities = Engine.getEngine().getNEntityStream().getEntities(ComponentType.HIT);
        Set<NEntity> colliders = Engine.getEngine().getNEntityStream().getEntities(ComponentType.DAMAGE);
        for (NEntity entity : entities) {
            checkColliders(entity, colliders);
        }
    }

    public void checkColliders(NEntity entity, Set<NEntity> colliders) {
        NEntity parent = ((LinkComponent) entity.getComponent(ComponentType.LINK)).getLink();
        CollisionComponent cc = (CollisionComponent) entity.getComponent(ComponentType.COLLISION);
        PositionComponent pc = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        MatrixD eTransform = MatrixUtil.getTransRotationMatrix(pc.getCoord(), pc.getRot());
        HitShape updated = cc.getHitShape().update(eTransform);
        for (NEntity collider : colliders) {
            if (collider != parent) {
                CollisionComponent ccc = (CollisionComponent) collider.getComponent(ComponentType.COLLISION);
                PositionComponent cpc = (PositionComponent) collider.getComponent(ComponentType.POSITION);
                MatrixD cTransform = MatrixUtil.getTransRotationMatrix(cpc.getCoord(), cpc.getRot());
                if (ccc.getHitShape().update(cTransform).collision(updated)) {
                    HitComponent hc = (HitComponent) entity.getComponent(ComponentType.HIT);
                    hc.hit(entity, collider);
                }
            }
        }
    }
}
