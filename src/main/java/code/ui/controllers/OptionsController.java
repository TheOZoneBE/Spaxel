package code.ui.controllers;

import code.engine.Engine;
import code.ui.elements.UIType;

/**
 * Created by theod on 19-9-2017.
 */
public class OptionsController extends Controller {
    public OptionsController() {
        super(UIType.OPTIONS);
    }

    public void gameSettings() {
        // TODO
    }

    public void controlsSettings() {
        // TODO
    }

    public void graphicsSettings() {
        // TODO
    }

    public void soundSettings() {
        // TODO
    }

    public void back() {
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UIType.MAIN));
    }
}
