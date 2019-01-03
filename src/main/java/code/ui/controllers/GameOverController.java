package code.ui.controllers;

import code.engine.Engine;
import code.ui.elements.UIType;
import code.engine.Resources;

/**
 * Created by theo on 26-6-2016.
 */
public final class GameOverController {
    private GameOverController() {
    }

    public static void play() {
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.CLASS_SELECTION));
    }

    public static void main() {
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.MAIN));
    }
}
