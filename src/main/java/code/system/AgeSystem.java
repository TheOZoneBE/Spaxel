package code.system;

import code.components.storage.age.AgeStorage;
import code.components.ComponentType;
import code.components.death.DeathComponent;
import code.engine.Engine;
import code.entity.Entity;
import code.system.SystemType;
import java.util.Set;

/**
 * The AgeSystem is responsible for updating all entities with an AgeComponent
 * 
 * Created by theo on 4/06/17.
 */
public class AgeSystem extends GameSystem {
    /**
     * Create a new AgeSystem
     */
    public AgeSystem() {
        super(SystemType.AGE);
    }

    public void update() {
        Set<Entity> nEntities = Engine.get().getNEntityStream().getEntities(ComponentType.AGE);
        for (Entity ne : nEntities) {
            AgeStorage ac = (AgeStorage) ne.getComponent(ComponentType.AGE);
            if (ac.getLife() != 0) {
                ac.setLife(ac.getLife() - 1);
            } else {
                DeathComponent dc = (DeathComponent) ne.getComponent(ComponentType.DEATH);
                if (dc != null) {
                    dc.die(ne);
                }
                ne.destroy();
            }
        }
    }
}
