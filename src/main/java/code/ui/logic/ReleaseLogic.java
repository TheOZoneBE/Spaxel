package code.ui.logic;

import code.engine.Engine;
import code.ui.elements.Element;

public class ReleaseLogic implements Logic {
    public void execute(Element element) {
        element.getState().setRelease(element.getState().isHover()
                && Engine.getEngine().getMouseWrapper().getMouse1().isRelease());
    }
}
