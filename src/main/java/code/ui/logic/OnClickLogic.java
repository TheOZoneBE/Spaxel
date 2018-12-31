package code.ui.logic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import code.ui.elements.Element;

/**
 * Implement an onClick behavior on an ui Element.
 * 
 * @see code#ui#elements#Element.
 */
public class OnClickLogic implements Logic {
    private static final Logger LOGGER = Logger.getLogger(OnClickLogic.class.getName());

    /**
     * Executes the method specified in the onClick property.
     * 
     * @param element The element with the onClick property.
     */
    public void update(Element element) {
        if (element.getState().isClick()) {
            String method = element.getStyle().getProperty("onClick");
            String controller = element.getStyle().getProperty("controller");
            if (method != null && controller != null) {
                try {
                    Method m = Class.forName(controller).getMethod(method);
                    m.invoke(controller);
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException
                        | ClassNotFoundException e) {
                    LOGGER.log(Level.SEVERE, e.toString(), e);
                }
            }
        }
    }
}
