package code.ui;

import code.engine.Engine;
import code.engine.SystemType;
import code.input.Keyboard;
import code.system.UISystem;

/**
 * Created by theo on 10-6-2016.
 */
public class PauseController extends Controller {

    public void update(){
        Keyboard k = Engine.getEngine().getKeyboard();
        if (k.escState.getState() && !k.escState.getPrevState()){
            resume();
        }
    }

    public void resume(){
        UISystem uis = (UISystem) Engine.getEngine().getSystem(SystemType.UI);
        uis.changeUI("play");
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
    }

    public void quit(){
        UISystem uis = (UISystem) Engine.getEngine().getSystem(SystemType.UI);
        uis.changeUI("main");
        Engine.getEngine().setGameState(Engine.GameState.MENU);
        Engine.getEngine().stopGame();
    }


}
