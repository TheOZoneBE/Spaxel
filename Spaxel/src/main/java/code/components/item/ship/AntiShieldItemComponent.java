package code.components.item.ship;

import code.components.ComponentType;
import code.components.cooldown.CooldownComponent;
import code.components.health.HealthComponent;
import code.components.hit.HitComponent;
import code.components.item.ShieldItemComponent;
import code.components.link.LinkComponent;
import code.components.position.PositionComponent;
import code.components.render.RenderComponent;
import code.engine.Engine;
import code.engine.NEntity;

import java.util.Set;

/**
 * Created by theod on 11-7-2017.
 */
public class AntiShieldItemComponent extends ShieldItemComponent {
    public AntiShieldItemComponent(int capacity, int maxCapacity, NEntity effect) {
        super("anti_shield", capacity, maxCapacity, effect);
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
                NEntity pParent = ((LinkComponent)p.getComponent(ComponentType.LINK)).getLink();
                if (pParent != parent && pc.getCoord().sum(ppc.getCoord().multiplicate(-1)).length() < 100){
                    HitComponent phc = (HitComponent)p.getComponent(ComponentType.HIT);
                    HealthComponent hc = (HealthComponent)parent.getComponent(ComponentType.HEALTH);
                    if (phc.getDamage() < capacity){
                        capacity -= phc.getDamage();
                        int healthGain = phc.getDamage()/4;
                        hc.setHealth(hc.getHealth() + healthGain > hc.getMaxHealth()? hc.getMaxHealth(): hc.getHealth() + healthGain);
                        Engine.getEngine().getNEntityStream().removeEntity(p);
                    }
                    else {
                        int healthGain = (phc.getDamage()-capacity)/4;
                        hc.setHealth(hc.getHealth() + healthGain > hc.getMaxHealth()? hc.getMaxHealth(): hc.getHealth() + healthGain);
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
