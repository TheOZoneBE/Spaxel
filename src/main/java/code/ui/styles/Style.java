package code.ui.styles;

import java.util.Map;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.HashSet;

public class Style {
    private Map<String, String> properties;
    private Set<String> enabled;

    public Style() {
        this.properties = new HashMap<>();
        this.enabled = new HashSet<>();
    }

    public Style(Map<String, String> properties, Set<String> enabled) {
        this.properties = properties;
        this.enabled = enabled;
    }

    public Style merge(Style style) {
        Map<String, String> mergedProperties = new HashMap<>(properties);
        Set<String> mergedEnabled = new HashSet<>(enabled);
        if (style != null) {
            mergedProperties.putAll(style.getProperties());
            mergedEnabled.addAll(style.getEnabled());
        }

        return new Style(mergedProperties, mergedEnabled);
    }

    public Style copy() {
        Map<String, String> mergedProperties = new HashMap<>(properties);
        Set<String> mergedEnabled = new HashSet<>(enabled);
        return new Style(mergedProperties, mergedEnabled);
    }

    public void disableProperty(String key) {
        enabled.remove(key);
    }

    public void enableProperty(String key) {
        enabled.add(key);
    }

    @JsonAnySetter
    public void setProperty(String key, String value) {
        properties.put(key, value);
        enabled.add(key);
    }

    public String getProperty(String key) {
        return properties.get(key);
    }

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
