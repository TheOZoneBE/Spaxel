package code.system;

import code.collision.HitShape;
import code.components.ComponentType;
import code.components.storage.hitshape.HitshapeStorage;
import code.components.hit.HitComponent;
import code.components.storage.transformation.TransformationStorage;
import code.engine.Engine;
import code.entity.Entity;
import code.system.SystemType;
import code.math.MatrixD;
import code.util.MatrixUtil;
import java.util.Set;

/**
 * The HitSystem is responsible for updating all the entities with a HitComponent
 * 
 * Created by theo on 20/06/17.
 */
public class HitSystem extends GameSystem {
    /**
     * Create a new HitSystem
     */
    public HitSystem() {
        super(SystemType.HIT);
    }

    public void update() {
        Set<Entity> entities = Engine.get().getNEntityStream().getEntities(ComponentType.HIT);
        Set<Entity> colliders = Engine.get().getNEntityStream().getEntities(ComponentType.DAMAGE);
        for (Entity entity : entities) {
            checkColliders(entity, colliders);
        }
    }

    /**
     * Check an entity with all colliders and perform logic when they collide
     * 
     * @param entity    the entity to check to
     * @param colliders the list of all entities the entity can collide with
     */
    public void checkColliders(Entity entity, Iterable<Entity> colliders) {
        Entity parent = entity.getParent();
        HitshapeStorage cc = (HitshapeStorage) entity.getComponent(ComponentType.HITSHAPE);
        TransformationStorage pc =
                (TransformationStorage) entity.getComponent(ComponentType.TRANSFORMATION);
        MatrixD eTransform = MatrixUtil.getTransRotationMatrix(pc.getPosition(), pc.getRotation());
        HitShape updated = cc.getHitShape().update(eTransform);
        for (Entity collider : colliders) {
            if (collider != parent) {
                HitshapeStorage ccc =
                        (HitshapeStorage) collider.getComponent(ComponentType.HITSHAPE);
                TransformationStorage cpc =
                        (TransformationStorage) collider.getComponent(ComponentType.TRANSFORMATION);
                MatrixD cTransform =
                        MatrixUtil.getTransRotationMatrix(cpc.getPosition(), cpc.getRotation());
                if (ccc.getHitShape().update(cTransform).collision(updated)) {
                    HitComponent hc = (HitComponent) entity.getComponent(ComponentType.HIT);
                    hc.hit(entity, collider);
                }
            }
        }
    }
}
