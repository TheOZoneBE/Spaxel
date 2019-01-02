package code.ui.controllers;

import code.engine.Engine;
import code.ui.elements.UIType;

/**
 * Created by theo on 26-6-2016.
 */
public class GameOverController extends Controller {

    public GameOverController() {
        super(UIType.GAME_OVER);
    }

    public void play() {
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UIType.CLASS_SELECTION));
    }

    public void main() {
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UIType.MAIN));
    }
}
