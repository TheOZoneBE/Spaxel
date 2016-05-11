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
import code.projectiles.Projectile;

public class ProjectileSystem extends GameSystem{

	public ProjectileSystem(Engine engine) {
		super(engine);
		type = SystemType.PROJECTILE;
	}
	
	public void update(){
		EntityStream entities = engine.getEntityStream();
		List<Entity> projs = entities.getEntities(EntityType.PROJECTILE);
		List<Entity> dead = new ArrayList<>();
		List<Entity> enemies = entities.getEntities(EntityType.ENEMY);
		List<Entity> shallowCopy = new ArrayList<>(projs);
		for (Entity proj : shallowCopy){
			proj.update();
			if (!((Projectile)proj).isAlive()){
				dead.add(proj);
			}
			else {
				for(Entity e: enemies){
					if(e.collision(proj)){
						Enemy temp = (Enemy)e;
						Projectile p = (Projectile)proj;
						p.hit(temp);
						//normal
						entities.addEntity(EntityType.SPAWNER, new ParticleSpawner(proj.getX(), proj.getY(), 10, 2, .2, 4, 150, temp.getSprite().getRandomPart(8,8)));
						//stresstest
						//entities.addEntity(EntityType.SPAWNER, new ParticleSpawner(proj.getX(), proj.getY(), 150, 100, .2, 4, 200, temp));
					}
				}
			}
		}
		projs.removeAll(dead);
	}

}
