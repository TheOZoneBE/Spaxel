package code.ui.elements;

import code.ui.styles.Style;
import java.util.List;
import java.util.ArrayList;
import code.ui.logic.Logic;
import code.ui.logic.OnClickLogic;
import code.ui.logic.OnUpdateLogic;
import code.ui.logic.ReleaseLogic;
import code.ui.render.StyleRenderer;
import code.ui.state.State;
import code.graphics.MasterBuffer;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import code.ui.logic.HoverLogic;
import code.ui.logic.ClickLogic;



public class Element {
    private static final String VISIBLE = "visible";
    private String id;
    private UI ui;
    private List<String> classes;

    private List<Element> children;

    private List<Logic> logic;

    private Style elementStyle;
    private Style hoverStyle;
    private Style clickStyle;

    private State state;

    public Element() {
        super();
        this.children = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.logic = new ArrayList<>();
        this.state = new State();
        logic.add(new HoverLogic());
        logic.add(new ClickLogic());
        logic.add(new ReleaseLogic());
        logic.add(new OnClickLogic());
        logic.add(new OnUpdateLogic());
    }

    public void render(MasterBuffer buffer) {
        Style style = elementStyle;
        if (state.isHover()) {
            style = elementStyle.merge(hoverStyle);
        }
        if (state.isClick()) {
            style = elementStyle.merge(clickStyle);
        }
        if (!style.contains(VISIBLE) || "true".equals(style.getProperty(VISIBLE))) {
            StyleRenderer.renderStyle(style, buffer);
            for (Element child : children) {
                child.render(buffer);
            }
        }
    }

    public void update() {
        if (!elementStyle.contains(VISIBLE) || "true".equals(elementStyle.getProperty(VISIBLE))) {
            for (Logic l : logic) {
                l.execute(this);
            }
            for (Element element : children) {
                element.update();
            }
        }
    }

    public void initStyle(Style style) {
        elementStyle = style;
        hoverStyle = new Style();
        clickStyle = new Style();
        for (String stl : classes) {
            elementStyle = elementStyle.merge(ui.getStyle("." + stl));
            hoverStyle = hoverStyle.merge(ui.getStyle("." + stl + ":hover"));
            clickStyle = clickStyle.merge(ui.getStyle("." + stl + ":click"));
        }
        elementStyle = elementStyle.merge(ui.getStyle("#" + id));
        hoverStyle = hoverStyle.merge(ui.getStyle("#" + id + ":hover"));
        clickStyle = clickStyle.merge(ui.getStyle("#" + id + ":click"));

        for (Element child : children) {
            child.initStyle(elementStyle.getMergeStyle());
        }
    }

    public void refreshStyle(Style style) {
        elementStyle = style.merge(elementStyle);
        for (Element element : children) {
            element.refreshStyle(elementStyle.getMergeStyle());
        }
    }

    public void reset() {
        state.reset();
        for (Element child : children) {
            child.reset();
        }
    }

    public Element findById(String id) {
        if (id.equals(this.id)) {
            return this;
        } else {
            for (Element child : children) {
                Element result = child.findById(id);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    public Style getElementStyle() {
        return elementStyle;
    }

    public void setStyleProperty(String key, String value) {
        elementStyle.setProperty(key, value);
        for (Element element : children) {
            element.refreshStyle(elementStyle.getMergeStyle());
        }
    }

    public State getState() {
        return state;
    }

    public void setUI(UI ui) {
        this.ui = ui;
        for (Element child : children) {
            child.setUI(ui);
        }
    }

    public UI getUI() {
        return ui;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonSetter("element")
    public void addElement(Element element) {
        this.children.add(element);
    }

    @JsonSetter("class")
    public void setClasses(String classes) {
        for (String className : classes.split(" ")) {
            this.classes.add(className);
        }
    }

    @JsonAnySetter()
    public void dump(String key, String value) {
        // NOTE dump all unknown xml properties in this hole
    }

    public void clearChildren() {
        children.clear();
    }
}
