package code.ui.logic;

import code.ui.elements.Element;
import code.util.UIUtil;

public class OnUpdateLogic implements Logic {
    public void execute(Element element) {
        String method = element.getElementStyle().getProperty("onUpdate");
        String controller = element.getUI().getController();
        if (method != null && controller != null) {
            UIUtil.invokeMethod(controller, method, element);
        }
    }
}
