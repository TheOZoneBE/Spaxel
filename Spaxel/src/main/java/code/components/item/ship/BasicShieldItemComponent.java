package code.components.item.ship;

import code.components.ComponentType;
import code.components.cooldown.CooldownComponent;
import code.components.hit.HitComponent;
import code.components.item.ItemComponent;
import code.components.item.ItemType;
import code.components.item.ShieldItemComponent;
import code.components.link.LinkComponent;
import code.components.position.PositionComponent;
import code.components.render.RenderComponent;
import code.engine.Engine;
import code.engine.NEntity;

import java.util.Set;

/**
 * Created by theo on 8/07/17.
 */
public class BasicShieldItemComponent extends ShieldItemComponent {

    public BasicShieldItemComponent(int capacity, int maxCapacity, NEntity effect) {
        super("basic_shield", capacity, maxCapacity, effect);
    }

    public void activate(NEntity entity){
        CooldownComponent cc = (CooldownComponent)entity.getComponent(ComponentType.COOLDOWN);

        if(cc.getCd() == 0){
            ((RenderComponent)effect.getComponent(ComponentType.RENDER)).setVisible(true);
            NEntity parent = ((LinkComponent)entity.getComponent(ComponentType.LINK)).getLink();
            PositionComponent pc = (PositionComponent)parent.getComponent(ComponentType.POSITION);
            Set<NEntity> projectiles = Engine.getEngine().getNEntityStream().getEntities(ComponentType.HIT);
            for (NEntity p: projectiles){
                PositionComponent ppc = (PositionComponent)p.getComponent(ComponentType.POSITION);
                if (pc.getCoord().sum(ppc.getCoord().multiplicate(-1)).length() < 100){
                    HitComponent phc = (HitComponent)p.getComponent(ComponentType.HIT);
                    if (phc.getDamage() < capacity){
                        capacity -= phc.getDamage();
                        Engine.getEngine().getNEntityStream().removeEntity(entity);
                    }
                    else {
                        phc.setDamage(phc.getDamage() -capacity);
                        capacity = maxCapacity;
                        cc.setCd(cc.getCdAmount());
                        ((RenderComponent)effect.getComponent(ComponentType.RENDER)).setVisible(false);
                    }
                }
            }
        }
    }
}
