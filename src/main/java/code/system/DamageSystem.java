package code.system;

import code.components.ComponentType;
import code.components.damage.Damage;
import code.components.damage.DamageComponent;
import code.components.health.HealthComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.engine.SystemType;
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
        Set<NEntity> entities =
                Engine.getEngine().getNEntityStream().getEntities(ComponentType.DAMAGE);
        for (NEntity e : entities) {
            DamageComponent dc = (DamageComponent) e.getComponent(ComponentType.DAMAGE);
            HealthComponent hc = (HealthComponent) e.getComponent(ComponentType.HEALTH);
            for (Damage d : dc.getDamages()) {
                hc.setHealth(hc.getHealth() - d.getDamage());
            }
            dc.getDamages().clear();
        }
    }
}
