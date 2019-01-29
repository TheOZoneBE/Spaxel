package code.components.behaviour.event;

import code.components.ComponentType;
import code.components.storage.cooldown.CooldownStorage;
import code.components.storage.move.MoveStorage;
import code.components.storage.shoot.ShootStorage;
import code.components.storage.transformation.TransformationStorage;
import code.components.storage.stack.StackStorage;
import code.components.storage.change.ChangeStorage;
import code.engine.Engine;
import code.entity.Entity;
import code.factories.entities.ProjectileIndustry;
import code.math.VectorD;
import code.engine.Resources;

/**
 * Created by theod on 28-6-2017.
 */
public class ShootHandler extends EventHandler {
    private static final double RADIAL_STEP = 0.05D;
    private static final double RADIAL_OFFSET = 2 * RADIAL_STEP;

    public ShootHandler() {
        super(EventHandlerType.SHOOT);
    }

    public void activate(Entity entity) {
        CooldownStorage cdStore = (CooldownStorage) entity.getComponent(ComponentType.COOLDOWN);
        if (cdStore.getCurrentCooldown() == 0) {
            Entity parent = entity.getParent();
            TransformationStorage pc =
                    (TransformationStorage) parent.getComponent(ComponentType.TRANSFORMATION);
            StackStorage sc = (StackStorage) entity.getComponent(ComponentType.STACK);
            ShootStorage shtStore = (ShootStorage) entity.getComponent(ComponentType.SHOOT);
            ProjectileIndustry pri = (ProjectileIndustry) Resources.get().getIndustryMap()
                    .get(shtStore.getProjectileFactory());

            double offset = (sc.getStacks() - 1) * -RADIAL_STEP;
            for (int i = 0; i <= (sc.getStacks() - 1); i++) {
                Entity projectile = pri.produce((TransformationStorage) pc.copy(), parent);
                MoveStorage pmc = (MoveStorage) projectile.getComponent(ComponentType.MOVE);
                double dx = Math.sin(pc.getRotation() + offset) * pmc.getSpeed();
                double dy = Math.cos(pc.getRotation() + offset) * pmc.getSpeed();
                projectile.addComponent(new ChangeStorage(new VectorD(dx, dy), 0, 0));
                Engine.get().getNEntityStream().addEntity(projectile);
                offset += RADIAL_OFFSET;
            }
            cdStore.startCooldown();
        }
    }
}
