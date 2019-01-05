package code.ui.elements;

import code.ui.styles.Style;
import java.util.List;
import java.util.ArrayList;
import code.ui.logic.Logic;
import code.ui.logic.OnClickLogic;
import code.ui.logic.OnUpdateLogic;
import code.ui.logic.OnInitLogic;
import code.ui.logic.ReleaseLogic;
import code.ui.render.StyleRenderer;
import code.ui.state.State;
import code.graphics.MasterBuffer;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import code.ui.logic.HoverLogic;
import code.ui.logic.ClickLogic;


/**
 * Represent an element of a UI
 */
public class Element {
    private static final String VISIBLE = "visible";
    private String id;
    private UI ui;
    private List<String> classes;

    private List<Element> children;

    private List<Logic> logic;

    private Style style;

    private State state;

    public Element() {
        super();
        this.children = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.logic = new ArrayList<>();
        this.state = new State();
        this.style = new Style();
        logic.add(new HoverLogic());
        logic.add(new ClickLogic());
        logic.add(new ReleaseLogic());
        logic.add(new OnClickLogic());
        logic.add(new OnUpdateLogic());
        logic.add(new OnInitLogic());
    }

    public void render(MasterBuffer buffer) {
        StyleRenderer.renderStyle(style, buffer);
        for (Element child : children) {
            child.render(buffer);
        }
    }

    public void update() {
        for (Logic l : logic) {
            l.execute(this);
        }
        for (Element element : children) {
            element.update();
        }
    }

    public void initStyle() {
        style.setElement(this);
        for (String stl : classes) {
            style.merge(ui.getStyle("." + stl));
            style.merge(ui.getStyle("." + stl + ":hover"), "hover");
            style.merge(ui.getStyle("." + stl + ":click"), "click");
        }
        style.merge(ui.getStyle("#" + id));
        style.merge(ui.getStyle("#" + id + ":hover"), "hover");
        style.merge(ui.getStyle("#" + id + ":click"), "click");

        for (Element child : children) {
            child.initStyle();
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

    public Style getStyle() {
        return style;
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

    public String getId() {
        return id;
    }

    @JsonSetter("element")
    public void addElement(Element element) {
        this.children.add(element);
        element.getStyle().setParent(style);
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
