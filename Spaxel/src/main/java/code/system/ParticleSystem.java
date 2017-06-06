package code.system;

import java.util.Iterator;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.SystemType;
import code.entity.Entity;

public class ParticleSystem extends GameSystem {

	public ParticleSystem() {
		super(SystemType.PARTICLE);
	}
	
	public void update(){
		//update all particles
		Iterator<Entity>  particles = Engine.getEngine().getEntityStream().getIterator(EntityType.HITPARTICLE);
		while(particles.hasNext()){
			Entity particle = particles.next();
			particle.update();
		}
	}

}
