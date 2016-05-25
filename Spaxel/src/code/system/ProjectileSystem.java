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

	public ProjectileSystem() {
		super(SystemType.PROJECTILE);
	}
	
	public void update(){
		EntityStream entities = Engine.getEngine().getEntityStream();
		List<Entity> projs = entities.getEntities(EntityType.PLAYER_PROJECTILE);
		List<Entity> enemies = entities.getEntities(EntityType.ENEMY);
		List<Entity> shallowCopy = new ArrayList<>(projs);
		for (Entity proj : shallowCopy){
			proj.update();
			if (((Projectile)proj).isAlive()){
				for(Entity e: enemies){
					if(e.collision(proj)){
						Enemy temp = (Enemy)e;
						Projectile p = (Projectile)proj;
						p.hit(temp);
						entities.addEntity(EntityType.SPAWNER, new ParticleSpawner(proj.getX(), proj.getY(), 5, 2, .2, 4, 150, temp.getSprite().getRandomPart(12,12)));
					}
				}
			}
		}
	}

}
