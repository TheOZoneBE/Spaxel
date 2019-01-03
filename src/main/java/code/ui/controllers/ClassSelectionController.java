package code.ui.controllers;

import code.engine.Engine;
import code.engine.NEntity;
import code.input.Keyboard;
import code.ui.elements.UIType;
import code.engine.Resources;

/**
 * Created by theo on 21-6-2016.
 */
public final class ClassSelectionController {

    public static void selectWhite() {
        Engine.get().setGameState(Engine.GameState.PLAY);
        NEntity player = Resources.get().getIndustryMap().get("player_white_industry").produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.PLAY));
    }

    public static void selectRed() {
        Engine.get().setGameState(Engine.GameState.PLAY);
        NEntity player = Resources.get().getIndustryMap().get("player_red_industry").produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.PLAY));
    }

    public static void selectGreen() {
        Engine.get().setGameState(Engine.GameState.PLAY);
        NEntity player = Resources.get().getIndustryMap().get("player_green_industry").produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.PLAY));
    }

    public static void selectBlue() {
        Engine.get().setGameState(Engine.GameState.PLAY);
        NEntity player = Resources.get().getIndustryMap().get("player_blue_industry").produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.PLAY));
    }

    public static void back() {
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.MAIN));
    }

    public static void escCheck() {
        Keyboard k = Engine.get().getKeyboard();
        if (k.getEscState().isDown() && !k.getEscState().hasBeenDown()) {
            back();
        }
    }


}
