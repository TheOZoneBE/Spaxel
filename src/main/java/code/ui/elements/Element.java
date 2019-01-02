package code.ui.elements;

import code.ui.styles.Style;
import java.util.List;
import java.util.ArrayList;
import code.ui.logic.Logic;
import code.ui.render.StyleRenderer;
import code.ui.state.State;
import code.graphics.MasterBuffer;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;



public class Element {
    private UI ui;
    private List<String> classes;

    private String id;
    private List<Element> children;
    private String text;

    private List<Logic> logic;
    private Style style;
    private State state;

    public Element() {
        super();
        this.children = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.logic = new ArrayList<>();
    }

    public void render(MasterBuffer buffer) {
        StyleRenderer.renderStyle(style, buffer);
        for (Element child : children) {
            child.render(buffer);
        }
    }

    public void update() {
        for (Logic l : logic) {
            l.update(this);
        }
        for (Element element : children) {
            element.update();
        }
    }

    public void refreshStyle(Style style) {
        // TODO state modifiers
        for (String stl : classes) {
            style = style.merge(ui.getStyle("." + stl));
        }
        style = style.merge(ui.getStyle("#" + id));
        this.style = style;
        for (Element element : children) {
            element.refreshStyle(style);
        }
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
    public void setText(String key, String value) {
        this.text = value;
    }
}
