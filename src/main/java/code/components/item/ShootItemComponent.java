package code.components.item;

import code.components.ComponentType;
import code.components.storage.cooldown.CooldownStorage;
import code.components.storage.move.MoveStorage;
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
public abstract class ShootItemComponent extends ItemComponent {
    private static final double RADIAL_STEP = 0.05D;
    private static final double RADIAL_OFFSET = 2 * RADIAL_STEP;
    String factory;

    public ShootItemComponent(ItemType itemType, String name, String factory) {
        super(itemType, name);
        this.factory = factory;
    }

    public void activate(Entity entity) {
        CooldownStorage cc = (CooldownStorage) entity.getComponent(ComponentType.COOLDOWN);
        if (cc.getCurrentCooldown() == 0) {
            Entity parent = entity.getParent();
            TransformationStorage pc =
                    (TransformationStorage) parent.getComponent(ComponentType.TRANSFORMATION);
            StackStorage sc = (StackStorage) entity.getComponent(ComponentType.STACK);
            ProjectileIndustry pri =
                    (ProjectileIndustry) Resources.get().getIndustryMap().get(factory);

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
            cc.setCurrentCooldown(cc.getMaxCooldown());
        }
    }
}
