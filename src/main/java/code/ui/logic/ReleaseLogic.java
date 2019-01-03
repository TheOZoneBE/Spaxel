package code.ui.logic;

import code.engine.Engine;
import code.ui.elements.Element;

/**
 * Logic to set the release property of an ui element
 */
public class ReleaseLogic implements Logic {
    public void execute(Element element) {
        element.getState().setRelease(element.getState().isHover()
                && Engine.get().getMouseWrapper().getMouse1().isRelease());
    }
}
