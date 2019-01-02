package code.ui.controllers;

import code.engine.Engine;
import code.input.Keyboard;
import code.ui.elements.UIType;

/**
 * Created by theo on 10-6-2016.
 */
public class PauseController extends Controller {

    public PauseController() {
        super(UIType.PAUSE);
    }

    @Override
    public void update() {
        super.update();
        Keyboard k = Engine.getEngine().getKeyboard();
        if (k.getEscState().isDown() && !k.getEscState().hasBeenDown()) {
            resume();
        }
    }

    public void resume() {
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UIType.PLAY));
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
    }

    public void quit() {
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UIType.MAIN));
        Engine.getEngine().setGameState(Engine.GameState.MENU);
        Engine.getEngine().stopGame();
    }

}
