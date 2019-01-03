package code.ui.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonSetter;
import code.engine.Resources;
import code.graphics.MasterBuffer;
import code.ui.styles.Style;

public class UI {
    private UIType type;
    private String controller;
    private List<String> styles;
    private Element body;

    public UI() {
        super();
        this.styles = new ArrayList<>();
    }

    public void initialize() {
        body.setUI(this);
        body.initStyle(new Style());
    }

    public void update() {
        body.update();
        // body.refreshStyle(new Style());
    }

    public void render(MasterBuffer buffer) {
        body.render(buffer);
    }

    public void reset() {
        body.reset();
        // body.refreshStyle(new Style());
    }

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
     * @return the styles
     */
    public List<String> getStyles() {
        return styles;
    }

    /**
     * @param styles the styles to set
     */
    public void setStyles(List<String> styles) {
        this.styles = styles;
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
    public void setRoot(Element body) {
        this.body = body;
    }

    @JsonSetter("style")
    public void addStyle(String style) {
        this.styles.add(style);
    }

    public Style getStyle(String name) {
        for (int i = styles.size() - 1; i >= 0; i--) {
            Map<String, Map<String, Style>> stylesheets = Resources.get().getStylesheets();
            styles.get(i);
            Map<String, Style> styley = stylesheets.get(styles.get(i));
            Style style = Resources.get().getStylesheets().get(styles.get(i)).get(name);
            if (style != null) {
                return style;
            }
        }
        return null;
    }

}
