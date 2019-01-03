package code.ui.controllers;

import code.engine.Engine;
import code.input.Keyboard;
import code.ui.elements.UIType;
import code.engine.Resources;

/**
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

    public static void escCheck() {
        Keyboard k = Engine.get().getKeyboard();
        if (k.getEscState().isDown() && !k.getEscState().hasBeenDown()) {
            back();
        }
    }

    public static void back() {
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.MAIN));
    }
}
