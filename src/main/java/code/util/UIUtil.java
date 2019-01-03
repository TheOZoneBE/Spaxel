package code.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;
import java.util.logging.Level;
import code.ui.elements.Element;

public final class UIUtil {
    private static final Logger LOGGER = Logger.getLogger(UIUtil.class.getName());

    private UIUtil() {
    }

    public static void invokeMethod(String controller, String method, Element element) {
        if (method.contains("update")) {
            invokeUpdateMethod(controller, method, element);
        } else {
            invokeActionMethod(controller, method);
        }
    }

    private static void invokeActionMethod(String controller, String method) {
        try {
            Method m = Class.forName(controller).getMethod(method);
            m.invoke(null);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException
                | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    private static void invokeUpdateMethod(String controller, String method, Element element) {
        try {
            Method m = Class.forName(controller).getMethod(method, Element.class);
            m.invoke(null, element);

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException
                | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }
}
