package code.system;

import java.util.List;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Entity;

public class AISystem extends GameSystem {

	public AISystem(Engine engine) {
		super(engine);
		type = SystemType.AI;
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		List<Entity> enemies = engine.getEntityStream().getEntities(EntityType.ENEMY);
		for (Entity e : enemies){
			e.update();
		}
	}

}
