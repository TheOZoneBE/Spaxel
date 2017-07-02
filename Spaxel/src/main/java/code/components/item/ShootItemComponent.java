package code.components.item;

import code.components.ComponentType;
import code.components.cooldown.CooldownComponent;
import code.components.link.LinkComponent;
import code.components.move.MoveComponent;
import code.components.position.PositionComponent;
import code.components.stack.StackComponent;
import code.components.velocity.VelocityComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.factories.entities.ProjectileIndustry;
import code.math.VectorF;

/**
 * Created by theod on 28-6-2017.
 */
public class ShootItemComponent extends ItemComponent {
    String factory;

    public ShootItemComponent(ItemType itemType, String name, String factory) {
        super(itemType, name);
        this.factory = factory;
    }

    public void activate(NEntity entity){
        CooldownComponent cc = (CooldownComponent)entity.getComponent(ComponentType.COOLDOWN);
        if (cc.getCd() == 0){
            NEntity parent = ((LinkComponent)entity.getComponent(ComponentType.LINK)).getLink();
            PositionComponent pc = (PositionComponent)parent.getComponent(ComponentType.POSITION);
            StackComponent sc = (StackComponent)entity.getComponent(ComponentType.STACK);
            ProjectileIndustry pri = (ProjectileIndustry) Engine.getEngine().getIndustryMap().get(factory);
            /*
            NEntity projectile = pri.produce((PositionComponent)pc.clone(), new LinkComponent(parent));
            MoveComponent pmc = (MoveComponent)projectile.getComponent(ComponentType.MOVE);
            float dx = (float)Math.sin(pc.getRot()) * pmc.getMaxSpeed();
            float dy = (float)Math.cos(pc.getRot()) * pmc.getMaxSpeed();
            projectile.addComponent(new VelocityComponent(new VectorF(dx, dy), 0));
            Engine.getEngine().getNEntityStream().addEntity(projectile);*/


            float offset = (sc.getStacks()-1)*-0.05f;
            for (int i = 0; i<=(sc.getStacks()-1); i++){
                NEntity projectile = pri.produce((PositionComponent)pc.clone(), new LinkComponent(parent));
                MoveComponent pmc = (MoveComponent)projectile.getComponent(ComponentType.MOVE);
                float dx = (float)Math.sin(pc.getRot() + offset) * pmc.getMaxSpeed();
                float dy = (float)Math.cos(pc.getRot() + offset) * pmc.getMaxSpeed();
                projectile.addComponent(new VelocityComponent(new VectorF(dx, dy), 0));
                Engine.getEngine().getNEntityStream().addEntity(projectile);
                offset += 0.1;
            }
            cc.setCd(cc.getCdAmount());
        }
    }
}
