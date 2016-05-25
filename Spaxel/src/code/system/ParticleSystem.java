package code.system;

import java.util.ArrayList;
import java.util.List;

import code.engine.Engine;
import code.engine.EntityStream;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Entity;
import code.entity.Particle;
import code.entity.ParticleSpawner;

public class ParticleSystem extends GameSystem {

	public ParticleSystem() {
		super(SystemType.PARTICLE);
	}
	
	public void update(){
		//update all spawners and acquire particles
		EntityStream entities = Engine.getEngine().getEntityStream();
		List<Entity> spawners = entities.getEntities(EntityType.SPAWNER);
		List<Entity> particles = new ArrayList<>();
		
		for (Entity spawner : spawners){
			spawner.update();
			if (((ParticleSpawner)spawner).isAlive()){
				particles.addAll(((ParticleSpawner)spawner).spawn());
			}
		}
		entities.addEntities(EntityType.PARTICLE, particles);
		//update all particles
		particles = entities.getEntities(EntityType.PARTICLE);
		for (Entity particle : particles){
			particle.update();
		}
	}

}
