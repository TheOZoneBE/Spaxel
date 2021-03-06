package code.ui.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonSetter;
import code.engine.Resources;
import code.graphics.buffer.MasterBuffer;

/**
 * Root class of an ui structure
 */
public class UI {
    private UIType type;
    private String controller;
    private List<String> styles;
    private Element body;

    /**
     * Create a new UI
     */
    public UI() {
        super();
        this.styles = new ArrayList<>();
    }

    /**
     * Initialize the ui by setting giving its reference to its children and initializing their
     * styles
     */
    public void initialize() {
        body.setUI(this);
        body.initStyle();
    }

    /**
     * Update this UI.
     */
    public void update() {
        body.update();
    }

    /**
     * Render this UI.
     * 
     * @param buffer the masterbuffer of the rendersystem
     */
    public void render(MasterBuffer buffer) {
        body.render(buffer);
    }

    /**
     * Reset this UI.
     */
    public void reset() {
        body.reset();
    }

    /**
     * Find an element in this UI by its id
     * 
     * @param id the id of the element
     * 
     * @return the element if found
     */
    public Element findById(String id) {
        return body.findById(id);
    }

    /**
     * @return the type
     */
    public UIType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(UIType type) {
        this.type = type;
    }

    /**
     * @return the controller
     */
    public String getController() {
        return controller;
    }

    /**
     * @param controller the controller to set
     */
    public void setController(String controller) {
        this.controller = controller;
    }

    /**
     * @return the body
     */
    public Element getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(Element body) {
        this.body = body;
    }

    /**
     * Add a new stylesheet path to this UI
     * 
     * @param style the path of the stylesheet
     */
    @JsonSetter("style")
    public void addStylesheetPath(String style) {
        this.styles.add(style);
    }

    /**
     * Get the style configuration corresponding to the given name
     * 
     * @param name the name of the style
     * 
     * @return the style configuration
     */
    public Map<String, String> getStyle(String name) {
        for (int i = styles.size() - 1; i >= 0; i--) {
            Map<String, String> style =
                    Resources.get().getStylesheets().get(styles.get(i)).get(name);
            if (style != null) {
                return style;
            }
        }
        return null;
    }

}
