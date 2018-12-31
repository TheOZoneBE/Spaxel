package code.ui.logic;

import code.ui.elements.Element;
import code.engine.Engine;

public class ClickLogic implements Logic {

    public void update(Element element) {
        if (element.getState().isHover() && Engine.getEngine().getMouseWrapper().isMouse1()) {
            element.getState().setClick(true);
        }
    }
}
