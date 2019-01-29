package code.system;

import code.components.ComponentType;
import code.components.storage.damage.Damage;
import code.components.storage.damage.DamageStorage;
import code.components.storage.health.HealthStorage;
import code.engine.Engine;
import code.entity.Entity;
import java.util.Set;

/**
 * The DamageSystem is responsible for updating all entities with a DamageComponent
 * 
 * Created by theo on 8/06/17.
 */
public class DamageSystem extends GameSystem {
    /**
     * Create a new DamageSystem
     */
    public DamageSystem() {
        super(SystemType.DAMAGE);
    }

    public void update() {
        Set<Entity> entities = Engine.get().getNEntityStream().getEntities(ComponentType.DAMAGE);
        for (Entity e : entities) {
            DamageStorage dc = (DamageStorage) e.getComponent(ComponentType.DAMAGE);
            HealthStorage hc = (HealthStorage) e.getComponent(ComponentType.HEALTH);
            for (Damage d : dc.getDamages()) {
                hc.setCurrentHealth(hc.getCurrentHealth() - d.getDamage());
            }
            dc.getDamages().clear();
        }
    }
}
