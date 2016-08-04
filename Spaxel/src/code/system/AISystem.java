package code.system;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.SystemType;
import code.entity.*;
import code.ui.UICounter;

public class AISystem extends GameSystem {

	public AISystem() {
		super(SystemType.AI);
	}
	
	public void update(){
		UICounter score = (UICounter)Engine.getEngine().getUIAtlas().get("play").getElement("score_counter");
		Random rand = new Random();
		Iterator<Entity> enemies = Engine.getEngine().getEntityStream().getIterator(EntityType.ENEMY);
		Player player = (Player)Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
		int k = 0;
		while (enemies.hasNext()){
			Entity e = enemies.next();
			((Enemy)e).updateAI(player);
			if (!e.isAlive()){
				Engine.getEngine().getEntityStream().addEntity(EntityType.SPAWNER, new ParticleSpawner(e.getX(), e.getY(), 5, 2, .5, 5, 300, ((Enemy)e).getSprite().getRandomPart(6,6)));
				score.addToCounter(100);
				player.setXp(player.getXp()+25);
				if (rand.nextInt(100) < 25){
					DroppedItem item = new DroppedItem(e.getX(), e.getY(), Engine.getEngine().getItems().getRandomItem(), 500);
					item.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_dropped_item"));
					Engine.getEngine().getEntityStream().addEntity(EntityType.DROPPEDITEM, item);
				}
			}
			k++;
		}
		if(k < 5){
			Enemy e = new Enemy(player.getX() + rand.nextInt(256) - 128, player.getY() + rand.nextInt(256) - 128,0,50,Engine.getEngine().getSpriteAtlas().get("green"), 20, .25);
			e.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_green"));
			e.update();
			Engine.getEngine().getEntityStream().addEntity(EntityType.ENEMY, e);
		}
	}

}
