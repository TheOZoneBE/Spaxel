package code.ui.controllers;

import code.engine.Engine;
import code.input.Keyboard;
import code.ui.elements.UIType;
import code.ui.elements.Element;
import code.engine.Resources;
import code.input.Key;

/**
 * Callbacks for elements in the options UI
 * 
 * Created by theod on 19-9-2017.
 */
public final class OptionsController {
    private OptionsController() {

    }

    /**
     * Opens the game settings
     */
    public static void gameSettings() {
        // TODO
    }

    /**
     * Opens the controls settings
     */
    public static void controlsSettings() {
        // TODO
    }

    /**
     * Opens the graphics settings
     */
    public static void graphicsSettings() {
        // TODO
    }

    /**
     * Opens the sound settings
     */
    public static void soundSettings() {
        // TODO
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
