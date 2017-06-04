package code.system;

import code.engine.*;
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
        NEntityStream nentities = Engine.getEngine().getNEntityStream();
        Iterator<Entity> spawners = entities.getIterator(EntityType.SPAWNER);
        List<NEntity> newParticles = new ArrayList<>();
        while(spawners.hasNext()){
            Entity spawner = spawners.next();
            newParticles.addAll(((ParticleSpawner)spawner).spawn());
        }
        nentities.addEntities(EntityType.PARTICLE, newParticles);
    }


}
