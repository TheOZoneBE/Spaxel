package code.system;

import code.engine.Engine;
import code.engine.EntityStream;
import code.engine.EntityType;
import code.engine.SystemType;
import code.entity.Entity;
import code.entity.ParticleSpawner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by theo on 14-6-2016.
 */
public class SpawnerSystem extends GameSystem {
    public SpawnerSystem() {
        super(SystemType.SPAWNER);
    }

    public void update(){
        //update all spawners and acquire particles
        EntityStream entities = Engine.getEngine().getEntityStream();
        Iterator<Entity> spawners = entities.getIterator(EntityType.SPAWNER);
        List<Entity> newParticles = new ArrayList<>();
        while(spawners.hasNext()){
            Entity spawner = spawners.next();
            newParticles.addAll(((ParticleSpawner)spawner).spawn());
        }
        entities.addEntities(EntityType.PARTICLE, newParticles);
    }


}
