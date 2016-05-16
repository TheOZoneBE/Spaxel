package code.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Enemy;
import code.entity.Entity;
import code.entity.ParticleSpawner;
import code.entity.Player;
import code.graphics.Sprite;

public class AISystem extends GameSystem {

	public AISystem(Engine engine) {
		super(engine);
		type = SystemType.AI;
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		Random rand = new Random();
		List<Entity> enemies = engine.getEntityStream().getEntities(EntityType.ENEMY);
		List<Entity> dead = new ArrayList<>();
		Player player = (Player)engine.getEntityStream().getEntities(EntityType.PLAYER).get(0);
		for (Entity e : enemies){
			((Enemy)e).update(player);
			if (!((Enemy)e).isAlive()){
				dead.add(e);
				engine.getEntityStream().addEntity(EntityType.SPAWNER, new ParticleSpawner(e.getX(), e.getY(), 5, 2, .5, 5, 300, ((Enemy)e).getSprite().getRandomPart(24,24)));
			}
		}
		enemies.removeAll(dead);
		if(enemies.size()<5){
			Enemy e = new Enemy(player.getX() + rand.nextInt(256) - 128, player.getY() + rand.nextInt(256) - 128,0,50,engine.getSpriteAtlas().get("green"));
			e.setHitShape(engine.getHitShapeAtlas().get("hitshape_green"));
			e.update(player);
			enemies.add(e);
		}
	}

}
