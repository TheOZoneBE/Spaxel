package code.system;

import java.util.ArrayList;
import java.util.List;

import code.engine.Engine;
import code.engine.EntityStream;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Enemy;
import code.entity.Entity;
import code.entity.ParticleSpawner;
import code.entity.Projectile;
import code.graphics.Sprite;

public class ProjectileSystem extends GameSystem{
	Sprite temp;

	public ProjectileSystem(Engine engine) {
		super(engine);
		type = SystemType.PROJECTILE;
		temp = new Sprite(4,4,2,0xffffffff);
	}
	
	public void update(){
		EntityStream entities = engine.getEntityStream();
		List<Entity> projs = entities.getEntities(EntityType.PROJECTILE);
		List<Entity> dead = new ArrayList<>();
		List<Entity> enemies = entities.getEntities(EntityType.ENEMY);
		for (Entity proj : projs){
			proj.update();
			if (!((Projectile)proj).isAlive()){
				dead.add(proj);
			}
			else {
				for(Entity e: enemies){
					if(e.collision(proj)){
						dead.add(proj);
						((Enemy)e).hit((Projectile)proj);
						//normal
						entities.addEntity(EntityType.SPAWNER, new ParticleSpawner(proj.getX(), proj.getY(), 15, 2, .2, 4, 150, temp));
						//stresstest
						//entities.addEntity(EntityType.SPAWNER, new ParticleSpawner(proj.getX(), proj.getY(), 150, 100, .2, 4, 200, temp));
					}
				}
			}
		}
		projs.removeAll(dead);
	}

}
