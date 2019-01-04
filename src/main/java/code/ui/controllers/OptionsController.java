package code.ui.controllers;

import code.engine.Engine;
import code.input.Keyboard;
import code.ui.elements.UIType;
import code.engine.Resources;

/**
 * Callbacks for elements in the options UI
 * 
 * Created by theod on 19-9-2017.
 */
public final class OptionsController {
    private OptionsController() {

    }

    public static void gameSettings() {
        // TODO
    }

    public static void controlsSettings() {
        // TODO
    }

    public static void graphicsSettings() {
        // TODO
    }

    public static void soundSettings() {
        // TODO
    }

    /**
     * Checks whether the esc key is pressed and goes to the previous screen if that is the case
     */
    public static void escCheck() {
        Keyboard k = Engine.get().getKeyboard();
        if (k.getEscState().isDown() && !k.getEscState().hasBeenDown()) {
            back();
        }
    }

    /**
     * Go to the previous screen
     */
    public static void back() {
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.MAIN));
    }
}
