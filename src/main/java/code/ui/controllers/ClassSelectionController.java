package code.ui.controllers;

import code.engine.Engine;
import code.engine.NEntity;
import code.ui.elements.UIType;

/**
 * Created by theo on 21-6-2016.
 */
public class ClassSelectionController extends Controller {

    public ClassSelectionController() {
        super(UIType.CLASS_SELECTION);
    }

    public void selectWhite() {
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        NEntity player = Engine.getEngine().getIndustryMap().get("player_white_industry").produce();
        Engine.getEngine().getNEntityStream().addEntity(player);
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UIType.PLAY));
    }

    public void selectRed() {
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        NEntity player = Engine.getEngine().getIndustryMap().get("player_red_industry").produce();
        Engine.getEngine().getNEntityStream().addEntity(player);
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UIType.PLAY));
    }

    public void selectGreen() {
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        NEntity player = Engine.getEngine().getIndustryMap().get("player_green_industry").produce();
        Engine.getEngine().getNEntityStream().addEntity(player);
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UIType.PLAY));
    }

    public void selectBlue() {
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        NEntity player = Engine.getEngine().getIndustryMap().get("player_blue_industry").produce();
        Engine.getEngine().getNEntityStream().addEntity(player);
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UIType.PLAY));
    }

    public void back() {
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UIType.MAIN));
    }
}
