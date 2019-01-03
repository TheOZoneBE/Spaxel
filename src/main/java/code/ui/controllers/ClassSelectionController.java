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
    private ClassSelectionController() {
    }

    public static void selectWhite() {
        NEntity player = Resources.get().getIndustryMap().get("player_white_industry").produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().getNEntityStream().cleanup();
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.PLAY));
        Engine.get().setGameState(Engine.EngineState.PLAY);
    }

    public static void selectRed() {
        NEntity player = Resources.get().getIndustryMap().get("player_red_industry").produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().getNEntityStream().cleanup();
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.PLAY));
        Engine.get().setGameState(Engine.EngineState.PLAY);
    }

    public static void selectGreen() {
        NEntity player = Resources.get().getIndustryMap().get("player_green_industry").produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().getNEntityStream().cleanup();
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.PLAY));
        Engine.get().setGameState(Engine.EngineState.PLAY);
    }

    public static void selectBlue() {
        NEntity player = Resources.get().getIndustryMap().get("player_blue_industry").produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().getNEntityStream().cleanup();
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.PLAY));
        Engine.get().setGameState(Engine.EngineState.PLAY);
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
