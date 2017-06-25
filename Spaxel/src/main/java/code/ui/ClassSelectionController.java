package code.ui;

import code.engine.Engine;
import code.engine.NEntity;
import code.engine.SystemType;
import code.entity.ActorType;
import code.system.UISystem;

/**
 * Created by theo on 21-6-2016.
 */
public class ClassSelectionController extends Controller{


    public ClassSelectionController() {
        super(UI.CLASS_SELECTION);
    }

    public void selectWhite(){
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        NEntity player = Engine.getEngine().getIndustryMap().get("player_white_industry").produce();
        Engine.getEngine().getNEntityStream().addEntity(player);
        //ActorType.WHITE.initialize();
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.PLAY));
    }

    public void selectRed(){
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        //TODO red player
        //ActorType.RED.initialize();
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.PLAY));
    }

    public void selectGreen(){
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        //TODO green player
        //ActorType.GREEN.initialize();
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.PLAY));
    }

    public void selectBlue(){
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        //TODO blue player
        //ActorType.BLUE.initialize();
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.PLAY));
    }
}
