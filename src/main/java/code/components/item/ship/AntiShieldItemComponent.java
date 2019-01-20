package code.components.item.ship;

import code.components.ComponentType;
import code.components.storage.cooldown.CooldownStorage;
import code.components.health.HealthComponent;
import code.components.hit.HitComponent;
import code.components.item.ShieldItemComponent;
import code.components.storage.transformation.TransformationStorage;
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
            TransformationStorage pc =
                    (TransformationStorage) parent.getComponent(ComponentType.TRANSFORMATION);
            Set<Entity> projectiles =
                    Engine.get().getNEntityStream().getEntities(ComponentType.HIT);
            for (Entity p : projectiles) {
                TransformationStorage ppc =
                        (TransformationStorage) p.getComponent(ComponentType.TRANSFORMATION);
                Entity pParent = p.getParent();
                if (pParent != parent && pc.getPosition().sum(ppc.getPosition().multiplicate(-1))
                        .length() < SHIELD_RADIUS) {
                    HitComponent phc = (HitComponent) p.getComponent(ComponentType.HIT);
                    HealthComponent hc =
                            (HealthComponent) parent.getComponent(ComponentType.HEALTH);
                    if (phc.getDamage() < capacity) {
                        capacity -= phc.getDamage();
                        int healthGain = phc.getDamage() / HEAL_DIVISION;
                        hc.setCurrentHealth(hc.getCurrentHealth() + healthGain > hc.getMaxHealth()
                                ? hc.getMaxHealth()
                                : hc.getCurrentHealth() + healthGain);
                        p.destroy();
                    } else {
                        int healthGain = (phc.getDamage() - capacity) / HEAL_DIVISION;
                        hc.setCurrentHealth(hc.getCurrentHealth() + healthGain > hc.getMaxHealth()
                                ? hc.getMaxHealth()
                                : hc.getCurrentHealth() + healthGain);
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
