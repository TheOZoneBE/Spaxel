package code.system;

import code.collision.HitShape;
import code.components.ComponentType;
import code.components.collision.CollisionComponent;
import code.components.hit.HitComponent;
import code.components.position.PositionComponent;
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
        CollisionComponent cc = (CollisionComponent) entity.getComponent(ComponentType.COLLISION);
        PositionComponent pc = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        MatrixD eTransform = MatrixUtil.getTransRotationMatrix(pc.getCoord(), pc.getRot());
        HitShape updated = cc.getHitShape().update(eTransform);
        for (Entity collider : colliders) {
            if (collider != parent) {
                CollisionComponent ccc =
                        (CollisionComponent) collider.getComponent(ComponentType.COLLISION);
                PositionComponent cpc =
                        (PositionComponent) collider.getComponent(ComponentType.POSITION);
                MatrixD cTransform =
                        MatrixUtil.getTransRotationMatrix(cpc.getCoord(), cpc.getRot());
                if (ccc.getHitShape().update(cTransform).collision(updated)) {
                    HitComponent hc = (HitComponent) entity.getComponent(ComponentType.HIT);
                    hc.hit(entity, collider);
                }
            }
        }
    }
}
