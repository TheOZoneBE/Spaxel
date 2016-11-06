package code.ui;

import code.engine.Engine;
import code.engine.SystemType;
import code.input.Keyboard;
import code.system.UISystem;

public class PlayController extends Controller{

    public void update(){
        Keyboard k = Engine.getEngine().getKeyboard();
        if (k.escState.getState() && !k.escState.getPrevState()){
            UISystem uis = (UISystem) Engine.getEngine().getSystem(SystemType.UI);
            uis.changeUI("pause");
            Engine.getEngine().setGameState(Engine.GameState.MENU);
        }
    }

}
