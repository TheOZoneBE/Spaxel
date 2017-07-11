package code.ui;

import code.components.ComponentType;
import code.components.ship.ShipComponent;
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
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.PLAY));
    }

    public void selectRed(){
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        NEntity player = Engine.getEngine().getIndustryMap().get("player_red_industry").produce();
        Engine.getEngine().getNEntityStream().addEntity(player);
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.PLAY));
    }

    public void selectGreen(){
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        NEntity player = Engine.getEngine().getIndustryMap().get("player_green_industry").produce();
        Engine.getEngine().getNEntityStream().addEntity(player);
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.PLAY));
    }

    public void selectBlue(){
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        NEntity player = Engine.getEngine().getIndustryMap().get("player_blue_industry").produce();
        Engine.getEngine().getNEntityStream().addEntity(player);
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.PLAY));
    }
}
