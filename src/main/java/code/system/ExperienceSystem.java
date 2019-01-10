package code.system;

import code.components.ComponentType;
import code.components.experience.ExperienceComponent;
import code.components.health.HealthComponent;
import code.engine.Engine;
import code.entity.Entity;
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
            ExperienceComponent ec =
                    (ExperienceComponent) entity.getComponent(ComponentType.EXPERIENCE);
            if (ec.getXpToLevel() <= ec.getXp()) {
                ec.setXp(ec.getXp() - ec.getXpToLevel());
                ec.setLevel(ec.getLevel() + 1);
                HealthComponent hc = (HealthComponent) entity.getComponent(ComponentType.HEALTH);
                hc.levelUp(ec.getLevel());
            }

        }
    }
}
