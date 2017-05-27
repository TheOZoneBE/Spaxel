package code.ui;

import code.engine.Engine;
import code.engine.SystemType;
import code.system.UISystem;

/**
 * Created by theo on 26-6-2016.
 */
public class GameOverController extends Controller {

    public void play(){
        UISystem uis = (UISystem)Engine.getEngine().getSystem(SystemType.UI);
        uis.changeUI("class_selection");
    }

    public void main(){
        UISystem uis = (UISystem) Engine.getEngine().getSystem(SystemType.UI);
        uis.changeUI("main");
    }
}
