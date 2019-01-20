package code.system;

import java.util.Set;
import code.components.Behaviour;
import code.components.ComponentType;
import code.engine.Engine;
import code.entity.Entity;
import code.engine.EntityStream;

/**
 * The SpawnerSystem is responsible for updating the entities with SpawnerComponents
 * 
 * Created by theo on 14-6-2016.
 */
public class SpawnerSystem extends GameSystem {
    /**
     * Create a new SpawnerSystem
     */
    public SpawnerSystem() {
        super(SystemType.SPAWNER);
    }

    public void update() {
        // update all spawners and acquire particles
        EntityStream nentities = Engine.get().getNEntityStream();
        Set<Entity> spawners = nentities.getEntities(ComponentType.SPAWN);
        for (Entity ne : spawners) {
            ((Behaviour) ne.getComponent(ComponentType.SPAWN)).execute(ne);
        }
    }

}
