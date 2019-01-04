package code.ui.controllers;

import code.engine.Engine;
import code.engine.NEntity;
import code.input.Keyboard;
import code.ui.elements.UIType;
import code.engine.Resources;

/**
 * Callbacks for element of the class selection UI
 * 
 * Created by theo on 21-6-2016.
 */
public final class ClassSelectionController {
    private ClassSelectionController() {
    }

    /**
     * Select the white ship.
     */
    public static void selectWhite() {
        NEntity player = Resources.get().getIndustryMap().get("player_white_industry").produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().getNEntityStream().cleanup();
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.PLAY));
        Engine.get().setEngineState(Engine.EngineState.PLAY);
    }

    /**
     * Select the red ship.
     */
    public static void selectRed() {
        NEntity player = Resources.get().getIndustryMap().get("player_red_industry").produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().getNEntityStream().cleanup();
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.PLAY));
        Engine.get().setEngineState(Engine.EngineState.PLAY);
    }

    /**
     * Select the green ship.
     */
    public static void selectGreen() {
        NEntity player = Resources.get().getIndustryMap().get("player_green_industry").produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().getNEntityStream().cleanup();
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.PLAY));
        Engine.get().setEngineState(Engine.EngineState.PLAY);
    }

    /**
     * Select the blue ship.
     */
    public static void selectBlue() {
        NEntity player = Resources.get().getIndustryMap().get("player_blue_industry").produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().getNEntityStream().cleanup();
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.PLAY));
        Engine.get().setEngineState(Engine.EngineState.PLAY);
    }

    /**
     * Go to the previous screen
     */
    public static void back() {
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.MAIN));
    }

    /**
     * Checks whether the esc key is pressed and goes to the previous screen if that is the case.
     */
    public static void escCheck() {
        Keyboard k = Engine.get().getKeyboard();
        if (k.getEscState().isDown() && !k.getEscState().hasBeenDown()) {
            back();
        }
    }


}
