package code.components.item.ship;

import code.components.ComponentType;
import code.components.storage.cooldown.CooldownStorage;
import code.components.hit.HitComponent;
import code.components.inventory.InventoryComponent;
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
public class ActiveShieldItemComponent extends ShieldItemComponent {
    private static final int SHIELD_RADIUS = 100;
    private static final int COOLDOWN_DIVISION = 4;

    public ActiveShieldItemComponent(int capacity, int maxCapacity, Entity effect) {
        super("active_shield", capacity, maxCapacity, effect);
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
                        int cdReduction = phc.getDamage() / COOLDOWN_DIVISION;
                        reduceCooldown(parent, cdReduction);
                        p.destroy();
                    } else {
                        int cdReduction = (phc.getDamage() - capacity) / COOLDOWN_DIVISION;
                        reduceCooldown(parent, cdReduction);
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

    public void reduceCooldown(Entity parent, int cdReduction) {
        reduceCooldown((InventoryComponent) parent.getComponent(ComponentType.PRIMARY),
                cdReduction);
        reduceCooldown((InventoryComponent) parent.getComponent(ComponentType.SECONDARY),
                cdReduction);
        reduceCooldown((InventoryComponent) parent.getComponent(ComponentType.SHIP), cdReduction);
    }

    public void reduceCooldown(InventoryComponent inv, int cdReduction) {
        for (Entity item : inv.getItems()) {
            CooldownStorage cc = (CooldownStorage) item.getComponent(ComponentType.COOLDOWN);
            if (cc != null && cc.getCurrentCooldown() != 0) {
                cc.setCurrentCooldown(cc.getCurrentCooldown() - cdReduction < 0 ? 0
                        : cc.getCurrentCooldown() - cdReduction);
            }
        }
    }

    public Component copy() {
        return new ActiveShieldItemComponent(capacity, maxCapacity, effect.copy());
    }
}
