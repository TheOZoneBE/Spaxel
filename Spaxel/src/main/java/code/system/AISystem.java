package code.system;

import java.util.Set;

import code.components.*;
import code.components.ai.AIComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.engine.SystemType;

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
