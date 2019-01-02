package code.components.death.actor;

import code.components.Component;
import code.components.death.DeathComponent;
import code.components.death.DeathType;
import code.engine.Engine;
import code.engine.NEntity;
import code.ui.elements.UIType;

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
        Engine.getEngine().stopGame();
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UIType.GAME_OVER));
        Engine.getEngine().setGameState(Engine.GameState.MENU);
    }

    public Component copy() {
        return new PlayerDeathComponent();
    }
}
