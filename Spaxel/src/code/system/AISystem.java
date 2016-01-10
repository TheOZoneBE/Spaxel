package code.system;

import java.util.ArrayList;
import java.util.List;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Enemy;
import code.entity.Entity;
import code.entity.ParticleSpawner;
import code.graphics.Sprite;

public class AISystem extends GameSystem {

	public AISystem(Engine engine) {
		super(engine);
		type = SystemType.AI;
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		List<Entity> enemies = engine.getEntityStream().getEntities(EntityType.ENEMY);
		List<Entity> dead = new ArrayList<>();
		for (Entity e : enemies){
			e.update();
			if (!((Enemy)e).isAlive()){
				dead.add(e);
				engine.getEntityStream().addEntity(EntityType.SPAWNER, new ParticleSpawner(e.getX(), e.getY(), 10, 5, .5, 5, 300, new Sprite(6,6,2,0xffff0000)));
			}
		}
		enemies.removeAll(dead);
	}

}
