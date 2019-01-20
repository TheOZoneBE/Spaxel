package code.components.item.ship;

import code.components.ComponentType;
import code.components.storage.cooldown.CooldownStorage;
import code.components.hit.HitComponent;
import code.components.item.ShieldItemComponent;
import code.components.storage.transformation.TransformationStorage;
import code.components.render.RenderComponent;
import code.engine.Engine;
import code.entity.Entity;
import code.components.Component;
import java.util.Set;

/**
 * Created by theo on 8/07/17.
 */
public class BasicShieldItemComponent extends ShieldItemComponent {
    private static final int SHIELD_RADIUS = 100;

    public BasicShieldItemComponent(int capacity, int maxCapacity, Entity effect) {
        super("basic_shield", capacity, maxCapacity, effect);
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
                    if (phc.getDamage() < capacity) {
                        capacity -= phc.getDamage();
                        p.destroy();
                    } else {
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
        return new BasicShieldItemComponent(capacity, maxCapacity, effect.copy());
    }
}
