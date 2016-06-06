package code.system;

import java.util.ArrayList;
import java.util.List;

import code.engine.Engine;
import code.engine.EntityStream;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Actor;
import code.entity.Enemy;
import code.entity.Entity;
import code.entity.ParticleSpawner;
import code.projectiles.Projectile;
import code.ui.UICounter;

public class ProjectileSystem extends GameSystem{

	public ProjectileSystem() {
		super(SystemType.PROJECTILE);
	}
	
	public void update(){
		UICounter score = (UICounter)Engine.getEngine().getUIAtlas().get("play").getElement("score_counter");
		EntityStream entities = Engine.getEngine().getEntityStream();
		List<Entity> projs = entities.getEntities(EntityType.PLAYER_PROJECTILE);
		List<Entity> enemies = entities.getEntities(EntityType.ENEMY);
		Engine.getEngine().getEntityStream().printLocks();
		for (Entity proj : projs){
			proj.update();
			if (((Projectile)proj).isAlive()){
				for(Entity e: enemies){
					if(e.collision(proj)){
						Actor temp = (Actor)e;
						Projectile p = (Projectile)proj;
						p.hit(temp);
						entities.addEntity(EntityType.SPAWNER, new ParticleSpawner(proj.getX(), proj.getY(), 5, 2, .2, 4, 150, temp.getSprite().getRandomPart(3,3)));
						score.addToCounter(p.getDamage());
					}
				}
			}
		}
		Engine.getEngine().getEntityStream().releaseLock(EntityType.ENEMY);
		Engine.getEngine().getEntityStream().releaseLock(EntityType.PLAYER_PROJECTILE);

		projs = entities.getEntities(EntityType.ENEMY_PROJECTILE);
		Entity player = entities.getEntities(EntityType.PLAYER).get(0);
		for (Entity proj : projs){
			proj.update();
			if (((Projectile)proj).isAlive()){
				if(player.collision(proj)){
					Actor temp = (Actor)player;
					Projectile p = (Projectile)proj;
					p.hit(temp);
					entities.addEntity(EntityType.SPAWNER, new ParticleSpawner(proj.getX(), proj.getY(), 5, 2, .2, 4, 150, temp.getSprite().getRandomPart(3,3)));
				}
			}
		}
		Engine.getEngine().getEntityStream().releaseLock(EntityType.PLAYER);
		Engine.getEngine().getEntityStream().releaseLock(EntityType.ENEMY_PROJECTILE);
	}

}
