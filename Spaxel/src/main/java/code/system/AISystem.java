package code.system;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import code.components.*;
import code.components.ai.AIComponent;
import code.components.position.PositionComponent;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.engine.SystemType;
import code.factories.entities.EnemyIndustry;
import code.math.VectorF;

public class AISystem extends GameSystem {

	public AISystem() {
		super(SystemType.AI);
	}
	
	public void update(){

		Random rand = new Random();
		Set<NEntity> enemies = Engine.getEngine().getNEntityStream().getEntities(ComponentType.AI);
		NEntity player = new ArrayList<>(Engine.getEngine().getNEntityStream().getEntities(EntityType.PLAYER)).get(0);
		PositionComponent playerPos = (PositionComponent)player.getComponent(ComponentType.POSITION);
		int k = 0;
		for (NEntity e: enemies){
			AIComponent aic = (AIComponent)e.getComponent(ComponentType.AI);

			aic.execute(e);
			k++;
		}
		//TODO move to its own system
		if(k < 1){
			/*Enemy e = new Enemy(player.getX() + rand.nextInt(256) - 128, player.getY() + rand.nextInt(256) - 128,0,50,Engine.getEngine().getSpriteAtlas().get("green"), 20,.25f, .25f);
			e.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_green"));
			e.update();
			Engine.getEngine().getEntityStream().addEntity(EntityType.ENEMY, e);*/
			EnemyIndustry ei = (EnemyIndustry)Engine.getEngine().getIndustryMap().get("enemy_green_industry");
			NEntity entity = ei.produce(
					new PositionComponent(new VectorF(playerPos.getCoord().getValue(0) + rand.nextInt(256) - 128, playerPos.getCoord().getValue(1) + rand.nextInt(256) - 128), 0));
			Engine.getEngine().getNEntityStream().addEntity(entity);
		}
	}

}
