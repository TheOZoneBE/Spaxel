package code.system;

import code.components.ComponentType;
import code.components.storage.experience.ExperienceStorage;
import code.components.storage.health.HealthStorage;
import code.engine.Engine;
import code.entity.Entity;
import code.util.EntityUtil;
import java.util.Set;

/**
 * The ExperienceSystem is responsible for updating all entities with an ExperienceComponent
 * 
 * Created by theo on 26/06/17.
 */
public class ExperienceSystem extends GameSystem {
    /**
     * Create a new ExperienceSystem
     */
    public ExperienceSystem() {
        super(SystemType.EXPERIENCE);
    }

    public void update() {
        Set<Entity> entities =
                Engine.get().getNEntityStream().getEntities(ComponentType.EXPERIENCE);
        for (Entity entity : entities) {
            ExperienceStorage ec =
                    (ExperienceStorage) entity.getComponent(ComponentType.EXPERIENCE);
            if (ec.ding()) {
                ec.levelUp();
                HealthStorage hc = (HealthStorage) entity.getComponent(ComponentType.HEALTH);
                int health = EntityUtil.healthAtLevel(ec.getLevel(), hc.getBaseHealth());
                hc.setMaxHealth(health);
                hc.setCurrentHealth(health);
            }

        }
    }
}
