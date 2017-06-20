package code.components.item.primary;

import code.components.ComponentType;
import code.components.cooldown.CooldownComponent;
import code.components.item.ItemComponent;
import code.components.item.ItemType;
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
public class BasicLaserItemComponent extends ItemComponent {
    public BasicLaserItemComponent() {
        super(ItemType.PRIMARY, "basic_laser");
    }

    public void activate(NEntity entity){
        CooldownComponent cc = (CooldownComponent)entity.getComponent(ComponentType.COOLDOWN);
        if (cc.getCd() == 0){
            NEntity parent = ((LinkComponent)entity.getComponent(ComponentType.LINK)).getLink();
            PositionComponent pc = (PositionComponent)parent.getComponent(ComponentType.POSITION);
            ProjectileIndustry pri = (ProjectileIndustry)Engine.getEngine().getIndustryMap().get("basic_laser_projectile_industry");
            NEntity projectile = pri.produce((PositionComponent)pc.clone(), new LinkComponent(parent));
            MoveComponent pmc = (MoveComponent)projectile.getComponent(ComponentType.MOVE);
            float dx = (float)Math.sin(pc.getRot()) * pmc.getMaxSpeed();
            float dy = (float)Math.cos(pc.getRot()) * pmc.getMaxSpeed();
            projectile.addComponent(new VelocityComponent(new VectorF(dx, dy), 0));
            Engine.getEngine().getNEntityStream().addEntity(projectile);
            cc.setCd(cc.getCdAmount());
        }
    }
}
