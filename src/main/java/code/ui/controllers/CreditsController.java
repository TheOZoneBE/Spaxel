package code.ui.controllers;

import code.engine.Engine;
import code.input.Keyboard;
import code.ui.elements.UI;

/**
 * Created by theo on 8-6-2016.
 */
public class CreditsController extends Controller {

    public CreditsController() {
        super(UI.CREDITS);
    }

    @Override
    public void update() {
        super.update();
        Keyboard k = Engine.getEngine().getKeyboard();
        if (k.getEscState().isDown() && !k.getEscState().hasBeenDown()) {
            back();
        }
    }

    public void back() {
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.MAIN));
    }
}