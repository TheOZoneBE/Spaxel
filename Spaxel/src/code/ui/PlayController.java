package code.ui;

import code.engine.Engine;
import code.engine.SystemType;
import code.system.UISystem;

public class PlayController extends Controller{

    public void update(){
        if (Engine.getEngine().getKeyboard().esc){
            UISystem uis = (UISystem) Engine.getEngine().getSystem(SystemType.UI);
            uis.changeUI("pause");
            Engine.getEngine().setGameState(Engine.GameState.MENU);
        }
    }

}
