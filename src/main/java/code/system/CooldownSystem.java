package code.system;

import code.components.ComponentType;
import code.components.storage.cooldown.CooldownStorage;
import code.engine.Engine;
import code.entity.Entity;
import java.util.Set;

/**
 * The CooldownSysten is responsible for updating all entities with a CooldownComponent
 * 
 * Created by theo on 20/06/17.
 */
public class CooldownSystem extends GameSystem {
    /**
     * Create a new CooldownSystem
     */
    public CooldownSystem() {
        super(SystemType.COOLDOWN);
    }

    public void update() {
        Set<Entity> entities = Engine.get().getNEntityStream().getEntities(ComponentType.COOLDOWN);
        for (Entity entity : entities) {
            CooldownStorage cc = (CooldownStorage) entity.getComponent(ComponentType.COOLDOWN);
            cc.setCurrentCooldown(cc.getCurrentCooldown() == 0 ? 0 : cc.getCurrentCooldown() - 1);
        }
    }
}
