package code.system;

import code.components.ComponentType;
import code.components.Behaviour;
import code.components.storage.health.HealthStorage;
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
        Set<Entity> entities = Engine.get().getNEntityStream().getEntities(ComponentType.HEALTH);
        for (Entity e : entities) {
            if (((HealthStorage) e.getComponent(ComponentType.HEALTH)).getCurrentHealth() < 0) {
                Behaviour dc = (Behaviour) e.getComponent(ComponentType.DEATH);
                if (dc != null) {
                    dc.execute(e);
                }
                e.destroy();
            }
        }
    }
}
