package code.system;

import code.components.ComponentType;
import code.components.input.InputComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.engine.SystemType;
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
        Set<NEntity> entities =
                Engine.getEngine().getNEntityStream().getEntities(ComponentType.INPUT);
        for (NEntity entity : entities) {
            ((InputComponent) entity.getComponent(ComponentType.INPUT)).update(entity);
        }
    }
}
