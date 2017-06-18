package code.components.item.primary;

import code.components.ComponentType;
import code.components.cooldown.CooldownComponent;
import code.components.item.ItemComponent;
import code.components.item.ItemType;
import code.components.link.LinkComponent;
import code.components.position.PositionComponent;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
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
            //TODO VERY TEMPORARY rework to factory if projectiles get reworked
            NEntity parent = ((LinkComponent)entity.getComponent(ComponentType.LINK)).getLink();
            PositionComponent pc = (PositionComponent)parent.getComponent(ComponentType.POSITION);
            Engine.getEngine().getEntityStream().addEntity(EntityType.PROJECTILE,
                    new BasicLaser(pc.getCoord().getValue(0), pc.getCoord().getValue(1), pc.getRot(),
                            Engine.getEngine().getSpriteAtlas().get("basic_laser_projectile"),
                            Engine.getEngine().getSpriteAtlas().get("white_trail"),
            10, 100, 300));
            cc.setCd(cc.getCdAmount());
        }
    }
}
