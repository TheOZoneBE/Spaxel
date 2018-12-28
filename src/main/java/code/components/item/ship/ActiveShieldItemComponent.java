package code.components.item.ship;

import code.components.ComponentType;
import code.components.cooldown.CooldownComponent;
import code.components.hit.HitComponent;
import code.components.inventory.InventoryComponent;
import code.components.item.ShieldItemComponent;
import code.components.link.LinkComponent;
import code.components.position.PositionComponent;
import code.components.render.RenderComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.components.Component;

import java.util.Set;

/**
 * Created by theod on 11-7-2017.
 */
public class ActiveShieldItemComponent extends ShieldItemComponent {
    private static final int SHIELD_RADIUS = 100;
    private static final int COOLDOWN_DIVISION = 4;

    public ActiveShieldItemComponent(int capacity, int maxCapacity, NEntity effect) {
        super("active_shield", capacity, maxCapacity, effect);
    }

    public void activate(NEntity entity) {
        CooldownComponent cc = (CooldownComponent) entity.getComponent(ComponentType.COOLDOWN);
        if (cc.getCd() == 0) {
            ((RenderComponent) effect.getComponent(ComponentType.RENDER)).setVisible(true);
            NEntity parent = ((LinkComponent) entity.getComponent(ComponentType.LINK)).getLink();
            PositionComponent pc = (PositionComponent) parent.getComponent(ComponentType.POSITION);
            Set<NEntity> projectiles = Engine.getEngine().getNEntityStream().getEntities(ComponentType.HIT);
            for (NEntity p : projectiles) {
                PositionComponent ppc = (PositionComponent) p.getComponent(ComponentType.POSITION);
                NEntity pParent = ((LinkComponent) p.getComponent(ComponentType.LINK)).getLink();
                if (pParent != parent && pc.getCoord().sum(ppc.getCoord().multiplicate(-1)).length() < SHIELD_RADIUS) {
                    HitComponent phc = (HitComponent) p.getComponent(ComponentType.HIT);
                    if (phc.getDamage() < capacity) {
                        capacity -= phc.getDamage();
                        int cdReduction = phc.getDamage() / COOLDOWN_DIVISION;
                        reduceCooldown(parent, cdReduction);
                        Engine.getEngine().getNEntityStream().removeEntity(p);
                    } else {
                        int cdReduction = (phc.getDamage() - capacity) / COOLDOWN_DIVISION;
                        reduceCooldown(parent, cdReduction);
                        phc.setDamage(phc.getDamage() - capacity);
                        capacity = maxCapacity;
                        cc.setCd(cc.getCdAmount());
                        ((RenderComponent) effect.getComponent(ComponentType.RENDER)).setVisible(false);
                    }
                }
            }
        }
    }

    public void reduceCooldown(NEntity parent, int cdReduction) {
        reduceCooldown((InventoryComponent) parent.getComponent(ComponentType.PRIMARY), cdReduction);
        reduceCooldown((InventoryComponent) parent.getComponent(ComponentType.SECONDARY), cdReduction);
        reduceCooldown((InventoryComponent) parent.getComponent(ComponentType.SHIP), cdReduction);
    }

    public void reduceCooldown(InventoryComponent inv, int cdReduction) {
        for (NEntity item : inv.getItems()) {
            CooldownComponent cc = (CooldownComponent) item.getComponent(ComponentType.COOLDOWN);
            if (cc != null && cc.getCd() != 0) {
                cc.setCd(cc.getCd() - cdReduction < 0 ? 0 : cc.getCd() - cdReduction);
            }
        }
    }

    public Component copy() {
        return new ActiveShieldItemComponent(capacity, maxCapacity, effect.copy());
    }
}
