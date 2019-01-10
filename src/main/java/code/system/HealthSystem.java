package code.system;

import code.components.ComponentType;
import code.components.death.DeathComponent;
import code.components.health.HealthComponent;
import code.engine.Engine;
import code.entity.Entity;
import java.util.Set;

/**
 * The HealthSystem is responsible for updating all entities with a HealthComponent
 * 
 * Created by theo on 8/06/17.
 */
public class HealthSystem extends GameSystem {
    /**
     * Create a new HealthSystem
     */
    public HealthSystem() {
        super(SystemType.HEALTH);
    }

    public void update() {
        Set<Entity> entities =
                Engine.get().getNEntityStream().getEntities(ComponentType.HEALTH);
        for (Entity e : entities) {
            if (((HealthComponent) e.getComponent(ComponentType.HEALTH)).getHealth() < 0) {
                DeathComponent dc = (DeathComponent) e.getComponent(ComponentType.DEATH);
                if (dc != null) {
                    dc.die(e);
                }
                Engine.get().getNEntityStream().removeEntity(e);
            }
        }
    }
}
