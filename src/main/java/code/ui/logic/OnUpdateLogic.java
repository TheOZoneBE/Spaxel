package code.ui.logic;

import code.ui.elements.Element;
import code.util.UIUtil;

/**
 * Logic to execute a method every update of an ui element
 */
public class OnUpdateLogic implements Logic {
    public void execute(Element element) {
        String method = element.getStyle().getProperty("onUpdate");
        String controller = element.getUI().getController();
        if (method != null && controller != null) {
            UIUtil.invokeUpdateMethod(controller, method, element);
        }
    }
}
