package code.ui;

import code.engine.Engine;

/**
 * Created by theod on 19-9-2017.
 */
public class OptionsController extends Controller {
    public OptionsController() {
        super(UI.OPTIONS);
    }

    public void gameSettings(){

    }

    public void controlsSettings(){

    }

    public void graphicsSettings(){

    }

    public void soundSettings(){

    }

    public void back(){
        Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.MAIN));
    }
}
