package code.ui.controllers;

import code.engine.Engine;
import code.input.Keyboard;
import code.ui.elements.UIType;

/**
 * Created by theo on 8-6-2016.
 */
public class CreditsController {
    public static void escCheck() {
        Keyboard k = Engine.getEngine().getKeyboard();
        if (k.getEscState().isDown() && !k.getEscState().hasBeenDown()) {
            back();
        }
    }

    public static void back() {
        Engine.getEngine().setCurrentUI(Engine.getEngine().getUIS().get(UIType.MAIN));
    }
}
