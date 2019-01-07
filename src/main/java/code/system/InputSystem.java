package code.system;

import code.components.ComponentType;
import code.components.input.InputComponent;
import code.engine.Engine;
import code.entity.Entity;
import code.system.SystemType;
import java.util.Set;

/**
 * The InputSystem is responsible for updating the entities with an InputComponent
 * 
 * Created by theo on 21/06/17.
 */
public class InputSystem extends GameSystem {
    /**
     * Create a new InputSystem
     */
    public InputSystem() {
        super(SystemType.INPUT);
    }

    public void update() {
        Set<Entity> entities =
                Engine.get().getNEntityStream().getEntities(ComponentType.INPUT);
        for (Entity entity : entities) {
            ((InputComponent) entity.getComponent(ComponentType.INPUT)).update(entity);
        }
    }
}
