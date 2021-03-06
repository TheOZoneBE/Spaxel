package code.ui.controllers;

import code.engine.Engine;
import code.input.Keyboard;
import code.ui.elements.UIType;
import code.engine.Resources;
import code.input.Key;
import code.ui.elements.Element;

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
     * 
     * @param element the calling element
     */
    public static void escCheck(Element element) {
        element.dump("nothing", "nothing");
        Keyboard k = Engine.get().getKeyboard();
        if (k.get(Key.ESC).isRelease()) {
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
