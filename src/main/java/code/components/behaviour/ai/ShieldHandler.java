package code.components.behaviour.ai;

import code.components.ComponentType;
import code.components.storage.transformation.TransformationStorage;
import code.engine.Engine;
import code.entity.Entity;
import java.util.Set;

/**
 * Created by theo on 8/07/17.
 */
public abstract class ShieldHandler extends AIHandler {
    protected static final int SHIELD_RADIUS = 100;

    public ShieldHandler(AIType type) {
        super(type);
    }

    public void execute(Entity entity) {
        Entity parent = entity.getParent();
        TransformationStorage trnsStore =
                (TransformationStorage) parent.getComponent(ComponentType.TRANSFORMATION);
        Set<Entity> projectiles = Engine.get().getNEntityStream().getEntities(ComponentType.HIT);
        for (Entity projectile : projectiles) {
            TransformationStorage prTrnsStore =
                    (TransformationStorage) projectile.getComponent(ComponentType.TRANSFORMATION);
            Entity pParent = projectile.getParent();
            if (pParent != parent && trnsStore.getPosition()
                    .sum(prTrnsStore.getPosition().multiplicate(-1)).length() < SHIELD_RADIUS) {
                handleProjectile(entity, projectile);
            }
        }
    }

    public abstract void handleProjectile(Entity entity, Entity projectile);
}
