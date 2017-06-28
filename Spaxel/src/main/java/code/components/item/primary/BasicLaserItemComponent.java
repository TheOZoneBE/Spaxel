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
import code.engine.EntityType;
import code.engine.NEntity;
import code.factories.entities.ProjectileIndustry;
import code.math.VectorF;
import code.projectiles.BasicLaser;

/**
 * Created by theo on 16/06/17.
 */
public class BasicLaserItemComponent extends ShootItemComponent {
    public BasicLaserItemComponent() {
        super(ItemType.PRIMARY, "basic_laser", "basic_laser_projectile_industry");
    }
}
