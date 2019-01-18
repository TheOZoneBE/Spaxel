package code.components.item.ship;

import code.components.ComponentType;
import code.components.cooldown.CooldownComponent;
import code.components.hit.HitComponent;
import code.components.item.ShieldItemComponent;
import code.components.position.PositionComponent;
import code.components.render.RenderComponent;
import code.components.velocity.VelocityComponent;
import code.engine.Engine;
import code.entity.Entity;
import code.components.Component;
import java.util.Set;

/**
 * Created by theod on 11-7-2017.
 */
public class ForceShieldItemComponent extends ShieldItemComponent {
    private static final int SHIELD_RADIUS = 100;

    public ForceShieldItemComponent(int capacity, int maxCapacity, Entity effect) {
        super("force_shield", capacity, maxCapacity, effect);
    }

    public void activate(Entity entity) {
        CooldownComponent cc = (CooldownComponent) entity.getComponent(ComponentType.COOLDOWN);
        if (cc.getCd() == 0) {
            ((RenderComponent) effect.getComponent(ComponentType.RENDER)).setVisible(true);
            Entity parent = entity.getParent();
            PositionComponent pc = (PositionComponent) parent.getComponent(ComponentType.POSITION);
            Set<Entity> projectiles =
                    Engine.get().getNEntityStream().getEntities(ComponentType.HIT);
            for (Entity p : projectiles) {
                PositionComponent ppc = (PositionComponent) p.getComponent(ComponentType.POSITION);
                Entity pParent = p.getParent();
                if (pParent != parent && pc.getCoord().sum(ppc.getCoord().multiplicate(-1))
                        .length() < SHIELD_RADIUS) {
                    HitComponent phc = (HitComponent) p.getComponent(ComponentType.HIT);
                    if (phc.getDamage() < capacity) {
                        capacity -= phc.getDamage();
                        VelocityComponent vc =
                                (VelocityComponent) p.getComponent(ComponentType.VELOCITY);
                        vc.setVelocity(vc.getVelocity().multiplicate(-1));
                    } else {
                        phc.setDamage(phc.getDamage() - capacity);
                        capacity = maxCapacity;
                        cc.setCd(cc.getCdAmount());
                        ((RenderComponent) effect.getComponent(ComponentType.RENDER))
                                .setVisible(false);
                    }
                }
            }
        }
    }

    public Component copy() {
        return new ForceShieldItemComponent(capacity, maxCapacity, effect.copy());
    }
}
