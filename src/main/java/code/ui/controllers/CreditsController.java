package code.ui.controllers;

import code.engine.Engine;
import code.input.Keyboard;
import code.ui.elements.UIType;
import code.engine.Resources;

/**
 * Callbacks for elements of the credits UI
 * 
 * Created by theo on 8-6-2016.
 */
public final class CreditsController {

    private CreditsController() {
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
