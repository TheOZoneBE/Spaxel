package code.ui;

import code.engine.Engine;
import code.input.Keyboard;

/**
 * Created by theo on 10-6-2016.
 */
public class PauseController extends Controller {

    public PauseController() {
        super(UI.PAUSE);
    }

    public void update() {
        super.update();
        Keyboard k = Engine.getEngine().getKeyboard();
        if (k.getEscState().isDown() && !k.getEscState().hasBeenDown()) {
            resume();
        }
    }

    public void resume() {
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.PLAY));
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
    }

    public void quit() {
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.MAIN));
        Engine.getEngine().setGameState(Engine.GameState.MENU);
        Engine.getEngine().stopGame();
    }

}
