package code.system;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import code.components.*;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.engine.SystemType;
import code.entity.*;
import code.factories.entities.EnemyIndustry;
import code.factories.entities.SpawnerIndustry;
import code.math.VectorF;
import code.ui.UICounter;

public class AISystem extends GameSystem {

	public AISystem() {
		super(SystemType.AI);
	}
	
	public void update(){
		UICounter score = (UICounter)Engine.getEngine().getUIAtlas().get("play").getElement("score_counter");
		Random rand = new Random();
		Set<NEntity> enemies = Engine.getEngine().getNEntityStream().getEntities(ComponentType.AI);
		Player player = (Player)Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
		int k = 0;
		SpawnerIndustry hpsi = (SpawnerIndustry)Engine.getEngine().getIndustryMap().get("hit_particle_spawner_industry");
		for (NEntity e: enemies){
			AIComponent aic = (AIComponent)e.getComponent(ComponentType.AI);

			aic.execute(new PositionComponent(new VectorF(player.getX(), player.getY()), player.getRot()), e);
			//TODO revisit
			//((Enemy)e).updateAI(player);
			/*
			if (!e.isAlive()){
				Engine.getEngine().getEntityStream().addEntity(EntityType.SPAWNER, new ParticleSpawner(e.getX(), e.getY(), 5, 2, .15f, 5, 300, ((Enemy)e).getSprite().getRandomPart(6,6)));
				//TODO revisit
				PositionComponent pc = new PositionComponent(new VectorF(e.getX(), e.getY()), 0);
				SpriteComponent sc = new SpriteComponent(((Enemy)e).getSprite().getRandomPart(6,6), 4);
				Engine.getEngine().getNEntityStream().addEntity(hpsi.produce(pc, sc));

				score.addToCounter(100);
				player.setXp(player.getXp()+25);
				if (rand.nextInt(100) < 25){
					DroppedItem item = new DroppedItem(e.getX(), e.getY(), Engine.getEngine().getItems().getRandomItem(), 500);
					item.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_dropped_item"));
					Engine.getEngine().getEntityStream().addEntity(EntityType.DROPPEDITEM, item);
				}
			}*/
			k++;
		}
		//TODO move to its own system
		if(k < 5){
			/*Enemy e = new Enemy(player.getX() + rand.nextInt(256) - 128, player.getY() + rand.nextInt(256) - 128,0,50,Engine.getEngine().getSpriteAtlas().get("green"), 20,.25f, .25f);
			e.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_green"));
			e.update();
			Engine.getEngine().getEntityStream().addEntity(EntityType.ENEMY, e);*/
			EnemyIndustry ei = (EnemyIndustry)Engine.getEngine().getIndustryMap().get("enemy_green_industry");
			NEntity entity = ei.produce(new VectorF(player.getX() + rand.nextInt(256) - 128, player.getY() + rand.nextInt(256) - 128), 0);
			Engine.getEngine().getNEntityStream().addEntity(entity);
		}
	}

}
