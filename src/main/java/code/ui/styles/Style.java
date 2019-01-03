package code.ui.styles;

import java.util.Map;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;

/**
 * Represents the styling applied to an ui Element
 */
public class Style {
    private static final List<String> nonMergeable =
            Arrays.asList("onClick", "onUpdate", "sprite", "text", "hitshape", "visible");
    private Map<String, String> properties;
    private Set<String> enabled;

    /**
     * Create a new empty Style object
     */
    public Style() {
        this.properties = new HashMap<>();
        this.enabled = new HashSet<>();
    }

    /**
     * Create a new style object with the given properties
     * 
     * @param properties the style properties
     * @param enabled    which of the properties are enabled
     */
    public Style(Map<String, String> properties, Set<String> enabled) {
        this.properties = properties;
        this.enabled = enabled;
    }

    /**
     * Merge this style with the given style and return a new style with the merged properties
     * 
     * @param style the style to merge with
     * 
     * @return a new style object with the merged properties
     */
    public Style merge(Style style) {
        Map<String, String> mergedProperties = new HashMap<>(properties);
        Set<String> mergedEnabled = new HashSet<>(enabled);
        if (style != null) {
            mergedProperties.putAll(style.getProperties());
            mergedEnabled.addAll(style.getEnabled());
        }

        return new Style(mergedProperties, mergedEnabled);
    }

    /**
     * Return a new style object with only the properties that can be propagated to child elements
     * 
     * @return a new style object with these properties
     */
    public Style getMergeStyle() {
        Map<String, String> mergeableProperties = new HashMap<>(properties);
        Set<String> mergeableEnabled = new HashSet<>(enabled);
        for (String key : nonMergeable) {
            mergeableProperties.remove(key);
        }
        mergeableEnabled.removeAll(nonMergeable);
        return new Style(mergeableProperties, mergeableEnabled);
    }

    /**
     * Disable a property of this style
     * 
     * @param key the name of the property
     */
    public void disableProperty(String key) {
        enabled.remove(key);
    }

    /**
     * Enable a property of this style
     * 
     * @param key the name of the property
     */
    public void enableProperty(String key) {
        enabled.add(key);
    }

    /**
     * Set a property of this style
     * 
     * @param key   the name of the property
     * @param value the value to set
     */
    @JsonAnySetter
    public void setProperty(String key, String value) {
        properties.put(key, value);
        enabled.add(key);
    }

    /**
     * Get a property of this style
     * 
     * @param key the name of the property
     * 
     * @return the value of the property
     */
    public String getProperty(String key) {
        return properties.get(key);
    }

    /**
     * Checks if the style contains a certain property
     * 
     * @param key the name of the property
     * 
     * @return true if the style contains the property
     */
    public boolean contains(String key) {
        return properties.containsKey(key);
    }

    /**
     * @return the properties
     */
    public Map<String, String> getProperties() {
        return properties;
    }

    /**
     * @return the enabled
     */
    public Set<String> getEnabled() {
        return enabled;
    }
}
