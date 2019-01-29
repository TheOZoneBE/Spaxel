package code.components.behaviour.hit;

import java.util.Set;
import code.collision.HitShape;
import code.components.Behaviour;
import code.components.ComponentType;
import code.components.storage.hitshape.HitshapeStorage;
import code.components.storage.transformation.TransformationStorage;
import code.engine.Engine;
import code.entity.Entity;
import code.math.MatrixD;
import code.util.MatrixUtil;

/**
 * Created by theo on 18/06/17.
 */
public class HitBehaviour extends Behaviour {
    private HitHandler handler;

    public HitBehaviour() {
        super(ComponentType.HIT);
    }

    public HitBehaviour(HitHandler handler) {
        super(ComponentType.HIT);
        this.handler = handler;
    }

    public void execute(Entity entity) {
        Set<Entity> colliders = Engine.get().getNEntityStream().getEntities(ComponentType.HITSHAPE);
        // Entity parent = entity.getParent();
        HitshapeStorage cc = (HitshapeStorage) entity.getComponent(ComponentType.HITSHAPE);
        TransformationStorage pc =
                (TransformationStorage) entity.getComponent(ComponentType.TRANSFORMATION);
        MatrixD eTransform = MatrixUtil.getTransRotationMatrix(pc.getPosition(), pc.getRotation());
        HitShape updated = cc.getHitShape().update(eTransform);
        for (Entity collider : colliders) {
            // if (collider != parent) {
            HitshapeStorage ccc = (HitshapeStorage) collider.getComponent(ComponentType.HITSHAPE);
            TransformationStorage cpc =
                    (TransformationStorage) collider.getComponent(ComponentType.TRANSFORMATION);
            MatrixD cTransform =
                    MatrixUtil.getTransRotationMatrix(cpc.getPosition(), cpc.getRotation());
            if (ccc.getHitShape().update(cTransform).collision(updated)) {
                handler.hit(entity, collider);
            }
            // }
        }
    }

    public HitBehaviour copy() {
        return new HitBehaviour(handler);
    }
}
