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

	public ParticleSystem(Engine engine) {
		super(engine);
		type = SystemType.PARTICLE;
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		//update all spawners and acquire particles
		EntityStream entities = engine.getEntityStream();
		List<Entity> spawners = entities.getEntities(EntityType.SPAWNER);
		List<Entity> dead = new ArrayList<>();
		List<Entity> particles = new ArrayList<>();
		
		for (Entity spawner : spawners){
			spawner.update();
			if (!((ParticleSpawner)spawner).isAlive()){
				dead.add(spawner);
			}
			else {
				particles.addAll(((ParticleSpawner)spawner).spawn());
			}
		}
		spawners.removeAll(dead);
		entities.addEntities(EntityType.PARTICLE, particles);
		//update all particles
		particles = entities.getEntities(EntityType.PARTICLE);
		dead.clear();
		for (Entity particle : particles){
			particle.update();
			if (!((Particle)particle).isAlive()){
				dead.add(particle);
			}
		}
		particles.removeAll(dead);
	}

}
