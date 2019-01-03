package code.ui.logic;

import code.ui.elements.Element;
import code.engine.Engine;

public class ClickLogic implements Logic {

    public void execute(Element element) {
        element.getState().setClick(element.getState().isHover()
                && Engine.getEngine().getMouseWrapper().getMouse1().isDown());
    }
}
