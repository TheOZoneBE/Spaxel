package code.components.item.ship;

import code.components.ComponentType;
import code.components.cooldown.CooldownComponent;
import code.components.hit.HitComponent;
import code.components.item.ShieldItemComponent;
import code.components.link.LinkComponent;
import code.components.position.PositionComponent;
import code.components.render.RenderComponent;
import code.components.velocity.VelocityComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.components.Component;

import java.util.Set;

/**
 * Created by theod on 11-7-2017.
 */
public class ForceShieldItemComponent extends ShieldItemComponent {
    public ForceShieldItemComponent(int capacity, int maxCapacity, NEntity effect) {
        super("force_shield", capacity, maxCapacity, effect);
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
                    if (phc.getDamage() < capacity){
                        capacity -= phc.getDamage();
                        VelocityComponent vc = (VelocityComponent)p.getComponent(ComponentType.VELOCITY);
                        vc.setVelocity(vc.getVelocity().multiplicate(-1));
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

    public Component copy(){
        return new ForceShieldItemComponent(capacity, maxCapacity, effect.copy());
    }
}
