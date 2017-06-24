package code.components.death;

import code.engine.Engine;
import code.engine.NEntity;
import code.engine.SystemType;
import code.system.UISystem;

/**
 * Created by theo on 24/06/17.
 */
public class PlayerDeathComponent extends DeathComponent {
    public PlayerDeathComponent() {
        super(DeathType.PLAYER);
    }

    public void die(NEntity entity){
        //add particle effect
        //show game over
        //TODO revisit maybe
        Engine.getEngine().stopGame();
        UISystem uis = (UISystem) Engine.getEngine().getSystem(SystemType.UI);
        uis.changeUI("game_over");
        Engine.getEngine().setGameState(Engine.GameState.MENU);
    }
}
