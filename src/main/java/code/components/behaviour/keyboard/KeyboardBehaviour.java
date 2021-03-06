package code.components.behaviour.keyboard;

import code.components.Behaviour;
import code.components.ComponentType;
import code.engine.Engine;
import code.entity.Entity;

public class KeyboardBehaviour extends Behaviour {
    private KeyboardHandler handler;

    public KeyboardBehaviour() {
        super(ComponentType.KEYBOARD);
    }

    public KeyboardBehaviour(KeyboardHandler handler) {
        super(ComponentType.KEYBOARD);
        this.handler = handler;
    }

    public void execute(Entity entity) {
        handler.handle(entity, Engine.get().getKeyboard());
    }

    /**
     * @return the handler
     */
    public KeyboardHandler getHandler() {
        return handler;
    }

    /**
     * @param handler the handler to set
     */
    public void setHandler(KeyboardHandler handler) {
        this.handler = handler;
    }


    public KeyboardBehaviour copy() {
        return new KeyboardBehaviour(handler);
    }
}
