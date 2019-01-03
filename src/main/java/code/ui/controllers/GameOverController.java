package code.ui.controllers;

import code.engine.Engine;
import code.ui.elements.UIType;

/**
 * Created by theo on 26-6-2016.
 */
public final class GameOverController {

    public static void play() {
        Engine.getEngine().setCurrentUI(Engine.getEngine().getUIS().get(UIType.CLASS_SELECTION));
    }

    public static void main() {
        Engine.getEngine().setCurrentUI(Engine.getEngine().getUIS().get(UIType.MAIN));
    }
}
