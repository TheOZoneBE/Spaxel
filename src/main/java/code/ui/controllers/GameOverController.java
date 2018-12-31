package code.ui.controllers;

import code.engine.Engine;
import code.ui.elements.UI;

/**
 * Created by theo on 26-6-2016.
 */
public class GameOverController extends Controller {

    public GameOverController() {
        super(UI.GAME_OVER);
    }

    public void play() {
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.CLASS_SELECTION));
    }

    public void main() {
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.MAIN));
    }
}
