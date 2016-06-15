package code.system;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import code.engine.Engine;
import code.engine.EntityStream;
import code.engine.EntityType;
import code.engine.SystemType;
import code.entity.Entity;
import code.entity.Particle;
import code.entity.ParticleSpawner;

public class ParticleSystem extends GameSystem {

	public ParticleSystem() {
		super(SystemType.PARTICLE);
	}
	
	public void update(){
		//update all particles
		Iterator<Entity>  particles = Engine.getEngine().getEntityStream().getIterator(EntityType.PARTICLE);
		while(particles.hasNext()){
			Entity particle = particles.next();
			particle.update();
		}
	}

}
