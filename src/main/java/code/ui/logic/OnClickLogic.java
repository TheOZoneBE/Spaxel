package code.ui.logic;

import code.ui.elements.Element;
import code.util.UIUtil;

/**
 * Implement an onClick behavior on an ui Element.
 * 
 * @see code.ui.elements.Element
 */
public class OnClickLogic implements Logic {
    /**
     * Executes the method specified in the onClick property.
     * 
     * @param element The element with the onClick property.
     */
    public void execute(Element element) {
        if (element.getState().isRelease()) {
            String method = element.getElementStyle().getProperty("onClick");
            String controller = element.getUI().getController();
            if (method != null && controller != null) {
                UIUtil.invokeMethod(controller, method, element);
            }
        }
    }
}
