package code.components.behaviour.ai;

import code.components.ComponentType;
import code.components.storage.cooldown.CooldownStorage;
import code.components.storage.projectile.ProjectileStorage;
import code.components.storage.shield.ShieldStorage;
import code.entity.Entity;

/**
 * Created by theo on 8/07/17.
 */
public class BasicShieldItemHandler extends ShieldHandler {

    public BasicShieldItemHandler() {
        super(AIType.BASIC_SHIELD);
    }

    public void handleProjectile(Entity entity, Entity projectile) {
        // TODO ((RenderComponent) effect.getComponent(ComponentType.RENDER)).setVisible(true);
        ProjectileStorage phc =
                (ProjectileStorage) projectile.getComponent(ComponentType.PROJECTILE);
        ShieldStorage shldStore = (ShieldStorage) entity.getComponent(ComponentType.SHIELD);

        if (phc.getDamage() < shldStore.getCurrentCapacity()) {
            shldStore.subCapacity(phc.getDamage());
            projectile.destroy();
        } else {
            CooldownStorage cc = (CooldownStorage) entity.getComponent(ComponentType.COOLDOWN);
            phc.subDamage(shldStore.getCurrentCapacity());
            shldStore.resetCapacity();
            cc.startCooldown();
            // TODO ((RenderBehaviour) effect.getComponent(ComponentType.RENDER)).setVisible(false);
        }
    }
}
