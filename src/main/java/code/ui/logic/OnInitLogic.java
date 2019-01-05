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
        if ((!element.getStyle().contains("visible")
                || "true".equals(element.getStyle().getProperty("visible")))
                && !element.getState().isInit()) {
            String method = element.getStyle().getProperty("onInit");
            String controller = element.getUI().getController();
            if (method != null && controller != null) {
                Element creation = UIUtil.invokeInitMethod(controller, method);

                element.addElement(creation);
                creation.initStyle();
            }
            element.getState().setInit(true);
        }
    }
}
