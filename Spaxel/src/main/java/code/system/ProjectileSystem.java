package code.system;

import java.util.Iterator;
import java.util.Set;

import code.components.*;
import code.components.collision.CollisionComponent;
import code.components.damage.Damage;
import code.components.damage.DamageComponent;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.engine.*;
import code.entity.Actor;
import code.entity.Entity;
import code.factories.entities.SpawnerIndustry;
import code.math.MatrixF;
import code.math.MatrixMaker;
import code.math.VectorF;
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
		SpawnerIndustry hpsi = (SpawnerIndustry)Engine.getEngine().getIndustryMap().get("hit_particle_spawner_industry");
		while (projs.hasNext()){
			Entity proj = projs.next();
			proj.update();
			if (proj.isAlive()){
				//Iterator<Entity> enemies = entities.getIterator(EntityType.ENEMY);
				Set<NEntity> colliders = Engine.getEngine().getNEntityStream().getEntities(ComponentType.COLLISION);
				for (NEntity e: colliders){
					CollisionComponent ecc = (CollisionComponent)e.getComponent(ComponentType.COLLISION);
					PositionComponent epc = (PositionComponent)e.getComponent(ComponentType.POSITION);
					MatrixF transform = MatrixMaker.getTransformationMatrix(epc.getCoord(), epc.getRot(), 1);
					if(ecc.getHitShape().update(transform).collision(proj.getUpdHitShape())){
						//Actor temp = (Actor)e;
						DamageComponent edc = (DamageComponent)e.getComponent(ComponentType.DAMAGE);
						SpriteComponent esc = (SpriteComponent)e.getComponent(ComponentType.SPRITE);
						Projectile p = (Projectile)proj;
						edc.addDamage(new Damage(p.getDamage()));
						//p.hit(temp);
						//entities.addEntity(EntityType.SPAWNER, new ParticleSpawner(proj.getX(), proj.getY(), 5, 2, .1f, 4, 150, temp.getSprite().getRandomPart(3,3)));
						//TODO revisit
						PositionComponent ppc = new PositionComponent(new VectorF(proj.getX(), proj.getY()), 0);
						SpriteComponent psc = new SpriteComponent(esc.getSprite().getRandomPart(3,3), esc.getScale());
						Engine.getEngine().getNEntityStream().addEntity(hpsi.produce(ppc, psc));
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
					//entities.addEntity(EntityType.SPAWNER, new ParticleSpawner(proj.getX(), proj.getY(), 5, 2, .1f, 4, 150, temp.getSprite().getRandomPart(3,3)));
					//TODO revisit
					PositionComponent pc = new PositionComponent(new VectorF(proj.getX(), proj.getY()), 0);
					SpriteComponent sc = new SpriteComponent(temp.getSprite().getRandomPart(3,3), 4);
					Engine.getEngine().getNEntityStream().addEntity(hpsi.produce(pc, sc));
				}
			}
		}
	}

}
