package code.ui.controllers;

import code.engine.Engine;
import code.engine.NEntity;
import code.input.Keyboard;
import code.ui.elements.UIType;

/**
 * Created by theo on 21-6-2016.
 */
public final class ClassSelectionController {

    public static void selectWhite() {
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        NEntity player = Engine.getEngine().getIndustryMap().get("player_white_industry").produce();
        Engine.getEngine().getNEntityStream().addEntity(player);
        Engine.getEngine().setCurrentUI(Engine.getEngine().getUIS().get(UIType.PLAY));
    }

    public static void selectRed() {
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        NEntity player = Engine.getEngine().getIndustryMap().get("player_red_industry").produce();
        Engine.getEngine().getNEntityStream().addEntity(player);
        Engine.getEngine().setCurrentUI(Engine.getEngine().getUIS().get(UIType.PLAY));
    }

    public static void selectGreen() {
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        NEntity player = Engine.getEngine().getIndustryMap().get("player_green_industry").produce();
        Engine.getEngine().getNEntityStream().addEntity(player);
        Engine.getEngine().setCurrentUI(Engine.getEngine().getUIS().get(UIType.PLAY));
    }

    public static void selectBlue() {
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        NEntity player = Engine.getEngine().getIndustryMap().get("player_blue_industry").produce();
        Engine.getEngine().getNEntityStream().addEntity(player);
        Engine.getEngine().setCurrentUI(Engine.getEngine().getUIS().get(UIType.PLAY));
    }

    public static void back() {
        Engine.getEngine().setCurrentUI(Engine.getEngine().getUIS().get(UIType.MAIN));
    }

    public static void escCheck() {
        Keyboard k = Engine.getEngine().getKeyboard();
        if (k.getEscState().isDown() && !k.getEscState().hasBeenDown()) {
            back();
        }
    }


}
