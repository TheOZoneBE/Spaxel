package code.ui.logic;

import code.ui.elements.Element;
import code.util.UIUtil;

/**
 * Implement an onInit behavior on an ui Element.
 * 
 * @see code.ui.elements.Element
 */
public class OnInitLogic implements Logic {
    /**
     * Executes the method specified in the onInit property.
     * 
     * @param element The element with the onInit property.
     */
    public void execute(Element element) {
        if (!element.getState().isInit()) {
            String method = element.getElementStyle().getProperty("onInit");
            String controller = element.getUI().getController();
            if (method != null && controller != null) {
                element.addElement(UIUtil.invokeInitMethod(controller, method));
            }
            element.getState().setInit(true);
        }
    }
}
