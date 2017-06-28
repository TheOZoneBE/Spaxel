package code.components.item.primary;

import code.components.ComponentType;
import code.components.cooldown.CooldownComponent;
import code.components.item.ItemComponent;
import code.components.item.ItemType;
import code.components.item.ShootItemComponent;
import code.components.link.LinkComponent;
import code.components.move.MoveComponent;
import code.components.position.PositionComponent;
import code.components.velocity.VelocityComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.factories.entities.ProjectileIndustry;
import code.math.VectorF;

/**
 * Created by theod on 28-6-2017.
 */
public class PiercingLaserItemComponent extends ShootItemComponent {
    public PiercingLaserItemComponent() {
        super(ItemType.PRIMARY, "piercing_laser", "piercing_laser_projectile_industry");
    }
}
