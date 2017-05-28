package code.system;

import java.util.Iterator;
import java.util.List;

import code.engine.Engine;
import code.engine.EntityStream;
import code.engine.EntityType;
import code.engine.SystemType;
import code.entity.Actor;
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
		Iterator<Entity> projs = entities.getIterator(EntityType.PLAYER_PROJECTILE);
		while (projs.hasNext()){
			Entity proj = projs.next();
			proj.update();
			if (proj.isAlive()){
				Iterator<Entity> enemies = entities.getIterator(EntityType.ENEMY);
				while(enemies.hasNext()){
					Entity e = enemies.next();
					if(e.collision(proj)){
						Actor temp = (Actor)e;
						Projectile p = (Projectile)proj;
						p.hit(temp);
						entities.addEntity(EntityType.SPAWNER, new ParticleSpawner(proj.getX(), proj.getY(), 5, 2, .1f, 4, 150, temp.getSprite().getRandomPart(3,3)));
						score.addToCounter(p.getDamage());
					}
				}
			}
		}

		projs = entities.getIterator(EntityType.ENEMY_PROJECTILE);
		Entity player = entities.getEntities(EntityType.PLAYER).get(0);
		while(projs.hasNext()){
			Entity proj = projs.next();
			proj.update();
			if (proj.isAlive()){
				if(player.collision(proj)){
					Actor temp = (Actor)player;
					Projectile p = (Projectile)proj;
					p.hit(temp);
					entities.addEntity(EntityType.SPAWNER, new ParticleSpawner(proj.getX(), proj.getY(), 5, 2, .1f, 4, 150, temp.getSprite().getRandomPart(3,3)));
				}
			}
		}
	}

}
