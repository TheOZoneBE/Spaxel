package code.ui.styles;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import code.ui.elements.Element;


/**
 * Represents the styling applied to an ui Element
 */
public class Style {
    private static final List<String> STYLE_SPECIFIC = Arrays.asList("onClick", "onUpdate",
            "onInit", "sprite", "text", "hitshape", "animation");

    private Style parent;
    private Element element;
    private Map<String, String> properties;

    /**
     * Create a new empty Style object
     */
    public Style() {
        this.properties = new HashMap<>();
    }

    public void setParent(Style parent) {
        this.parent = parent;
    }

    public Style getParent() {
        return parent;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public String getProperty(String key) {
        for (String mod : element.getState().getModifiers()) {
            if (properties.containsKey(key + ":" + mod)) {
                return properties.get(key + ":" + mod);
            }
        }
        if (properties.containsKey(key)) {
            return properties.get(key);
        }
        String result = null;
        if (parent != null && !STYLE_SPECIFIC.contains(key)) {
            result = parent.getProperty(key);
        }
        return result;
    }

    /**
     * Checks if the style contains a certain property
     * 
     * @param key the name of the property
     * 
     * @return true if the style contains the property
     */
    public boolean contains(String key) {
        for (String mod : element.getState().getModifiers()) {
            if (properties.containsKey(key + ":" + mod)) {
                return true;
            }
        }
        if (properties.containsKey(key)) {
            return true;
        }
        boolean result = false;
        if (parent != null && !STYLE_SPECIFIC.contains(key)) {
            result = parent.contains(key);
        }
        return result;
    }



    public void merge(Map<String, String> props, String mod) {
        if (props != null) {
            for (Map.Entry<String, String> entry : props.entrySet()) {
                properties.put(entry.getKey() + ":" + mod, entry.getValue());
            }
        }
    }

    public void merge(Map<String, String> props) {
        if (props != null) {
            properties.putAll(props);
        }
    }

    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    public void setProperty(String key, String value, String mod) {
        properties.put(key + ":" + mod, value);
    }
}


