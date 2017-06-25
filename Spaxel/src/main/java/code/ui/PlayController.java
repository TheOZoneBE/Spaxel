package code.ui;

import code.engine.Engine;
import code.engine.SystemType;
import code.input.Keyboard;
import code.system.UISystem;

public class PlayController extends Controller{

    public PlayController() {
        super(UI.PLAY);
    }

    public void update(){
        //TODO update score and labels and items
        Keyboard k = Engine.getEngine().getKeyboard();
        if (k.escState.getState() && !k.escState.getPrevState()){
            Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.PAUSE));
            Engine.getEngine().setGameState(Engine.GameState.PAUSE);
        }
    }

}
