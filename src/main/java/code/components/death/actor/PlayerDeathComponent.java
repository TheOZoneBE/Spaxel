package code.components.death.actor;

import code.components.Component;
import code.components.death.DeathComponent;
import code.components.death.DeathType;
import code.engine.Engine;
import code.engine.NEntity;
import code.ui.elements.UIType;
import code.engine.Resources;

/**
 * Created by theo on 24/06/17.
 */
public class PlayerDeathComponent extends DeathComponent {
    public PlayerDeathComponent() {
        super(DeathType.PLAYER);
    }

    public void die(NEntity entity) {
        // add particle effect
        // show game over
        // TODO revisit maybe
        Engine.get().stopGame();
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.GAME_OVER));
        Engine.get().setGameState(Engine.EngineState.MENU);
    }

    public Component copy() {
        return new PlayerDeathComponent();
    }
}
