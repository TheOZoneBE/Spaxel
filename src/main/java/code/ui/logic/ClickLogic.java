package code.ui.logic;

import code.ui.elements.Element;
import code.engine.Engine;

/**
 * Logic to set the click property of an ui element
 */
public class ClickLogic implements Logic {

    public void execute(Element element) {
        element.getState().setClick(element.getState().isHover()
                && Engine.getEngine().getMouseWrapper().getMouse1().isDown());
    }
}
