package code.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import code.components.ComponentType;
import code.components.spawner.SpawnerComponent;
import code.engine.Engine;
import code.entity.EntityType;
import code.entity.Entity;
import code.engine.EntityStream;
import code.system.SystemType;

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
        Set<Entity> spawners = nentities.getEntities(ComponentType.SPAWNER);
        List<Entity> newParticles = new ArrayList<>();
        for (Entity ne : spawners) {
            newParticles
                    .addAll(((SpawnerComponent) ne.getComponent(ComponentType.SPAWNER)).spawn(ne));
        }
        nentities.addEntities(EntityType.HITPARTICLE, newParticles);
    }

}
