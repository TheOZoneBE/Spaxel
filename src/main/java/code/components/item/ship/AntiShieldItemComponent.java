package code.components.item.ship;

import code.components.ComponentType;
import code.components.storage.cooldown.CooldownStorage;
import code.components.health.HealthComponent;
import code.components.hit.HitComponent;
import code.components.item.ShieldItemComponent;
import code.components.position.PositionComponent;
import code.components.render.RenderComponent;
import code.engine.Engine;
import code.entity.Entity;
import code.components.Component;
import java.util.Set;

/**
 * Created by theod on 11-7-2017.
 */
public class AntiShieldItemComponent extends ShieldItemComponent {
    private static final int SHIELD_RADIUS = 100;
    private static final int HEAL_DIVISION = 4;

    public AntiShieldItemComponent(int capacity, int maxCapacity, Entity effect) {
        super("anti_shield", capacity, maxCapacity, effect);
    }

    public void activate(Entity entity) {
        CooldownStorage cc = (CooldownStorage) entity.getComponent(ComponentType.COOLDOWN);
        if (cc.getCurrentCooldown() == 0) {
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
                    HealthComponent hc =
                            (HealthComponent) parent.getComponent(ComponentType.HEALTH);
                    if (phc.getDamage() < capacity) {
                        capacity -= phc.getDamage();
                        int healthGain = phc.getDamage() / HEAL_DIVISION;
                        hc.setHealth(
                                hc.getHealth() + healthGain > hc.getMaxHealth() ? hc.getMaxHealth()
                                        : hc.getHealth() + healthGain);
                        p.destroy();
                    } else {
                        int healthGain = (phc.getDamage() - capacity) / HEAL_DIVISION;
                        hc.setHealth(
                                hc.getHealth() + healthGain > hc.getMaxHealth() ? hc.getMaxHealth()
                                        : hc.getHealth() + healthGain);
                        phc.setDamage(phc.getDamage() - capacity);
                        capacity = maxCapacity;
                        cc.setCurrentCooldown(cc.getMaxCooldown());
                        ((RenderComponent) effect.getComponent(ComponentType.RENDER))
                                .setVisible(false);
                    }
                }
            }
        }
    }

    public Component copy() {
        return new AntiShieldItemComponent(capacity, maxCapacity, effect.copy());
    }
}
