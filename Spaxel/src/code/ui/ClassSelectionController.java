package code.ui;

import code.engine.Engine;
import code.engine.SystemType;
import code.entity.ActorType;
import code.system.UISystem;

/**
 * Created by theo on 21-6-2016.
 */
public class ClassSelectionController extends Controller{

    public void selectWhite(){
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        ActorType.WHITE.initialize();
        UISystem uis = (UISystem) Engine.getEngine().getSystem(SystemType.UI);
        uis.changeUI("play");
    }

    public void selectRed(){
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        ActorType.RED.initialize();
        UISystem uis = (UISystem) Engine.getEngine().getSystem(SystemType.UI);
        uis.changeUI("play");
    }

    public void selectGreen(){
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        ActorType.GREEN.initialize();
        UISystem uis = (UISystem) Engine.getEngine().getSystem(SystemType.UI);
        uis.changeUI("play");
    }

    public void selectBlue(){
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        ActorType.BLUE.initialize();
        UISystem uis = (UISystem) Engine.getEngine().getSystem(SystemType.UI);
        uis.changeUI("play");
    }
}
