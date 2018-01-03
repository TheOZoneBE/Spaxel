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
		Set<NEntity> enemies = Engine.getEngine().getNEntityStream().getEntities(ComponentType.AI);
		for (NEntity e: enemies){
			AIComponent aic = (AIComponent)e.getComponent(ComponentType.AI);

			aic.execute(e);
		}
	}

}
