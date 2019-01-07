package code.system;

import java.util.Set;
import code.components.ComponentType;
import code.components.ai.AIComponent;
import code.engine.Engine;
import code.entity.Entity;
import code.system.SystemType;

/**
 * The AISystem is responsible for updating all entities with an AI component
 */
public class AISystem extends GameSystem {
	/**
	 * Create a new AISystem
	 */
	public AISystem() {
		super(SystemType.AI);
	}

	public void update() {
		Set<Entity> enemies = Engine.get().getNEntityStream().getEntities(ComponentType.AI);

		for (Entity e : enemies) {
			AIComponent aic = (AIComponent) e.getComponent(ComponentType.AI);

			aic.execute(e);
		}
	}

}
