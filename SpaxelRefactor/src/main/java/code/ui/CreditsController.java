package code.ui;

import code.engine.Engine;
import code.engine.SystemType;
import code.input.Keyboard;
import code.system.UISystem;

/**
 * Created by theo on 8-6-2016.
 */
public class CreditsController extends Controller {

    public void update(){
        Keyboard k = Engine.getEngine().getKeyboard();
        if (k.escState.getState() && !k.escState.getPrevState()){

            back();
        }
    }

    public void back(){
        UISystem uis = (UISystem) Engine.getEngine().getSystem(SystemType.UI);
        uis.changeUI("main");
    }
}
