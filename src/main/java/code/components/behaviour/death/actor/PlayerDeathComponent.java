package code.components.behaviour.death.actor;

import code.components.behaviour.death.DeathHandler;
import code.components.behaviour.death.DeathType;
import code.engine.Engine;
import code.entity.Entity;
import code.ui.elements.UIType;
import code.engine.Resources;

/**
 * Created by theo on 24/06/17.
 */
public class PlayerDeathComponent extends DeathHandler {
    public PlayerDeathComponent() {
        super(DeathType.PLAYER);
    }

    public void die(Entity entity) {
        // add particle effect
        // show game over
        // TODO revisit maybe
        Engine.get().stopGame();
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.GAME_OVER));
        Engine.get().setEngineState(Engine.EngineState.MENU);
    }

}
