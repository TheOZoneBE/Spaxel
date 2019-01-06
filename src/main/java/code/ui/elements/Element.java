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
import code.graphics.buffer.MasterBuffer;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import code.ui.logic.HoverLogic;
import code.ui.logic.ClickLogic;


/**
 * Represent an element of a UI
 */
public class Element {
    private int index;
    private String id;
    private UI ui;
    private List<String> classes;

    private List<Element> children;

    private List<Logic> logic;

    private Style style;

    private State state;

    /**
     * Create a new Element
     */
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

    /**
     * Render this element with the given buffer
     * 
     * @param buffer the master buffer of the game
     */
    public void render(MasterBuffer buffer) {
        StyleRenderer.renderStyle(style, buffer);
        synchronized (children) {
            for (Element child : children) {
                child.render(buffer);
            }
        }
    }

    /**
     * Update this element
     */
    public void update() {
        for (Logic l : logic) {
            l.execute(this);
        }
        for (Element element : children) {
            element.update();
        }
    }

    /**
     * Initialize the style of this element
     */
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

    /**
     * Reset the state of this element
     */
    public void reset() {
        state.reset();
        for (Element child : children) {
            child.reset();
        }
    }

    /**
     * Find an element by id in the subtree where this element is the root
     * 
     * @param id the id to find
     * 
     * @return the found Element
     */
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

    /**
     * Add a new element to the children of this element
     * 
     * @param element the element to add
     */
    @JsonSetter("element")
    public void addElement(Element element) {
        synchronized (children) {
            this.children.add(element);
            element.getStyle().setParent(style);
            element.setIndex(children.size() - 1);
            element.setUI(ui);
            element.getStyle().setElement(element);
        }
    }

    @JsonSetter("class")
    public void setClasses(String classes) {
        for (String className : classes.split(" ")) {
            this.classes.add(className);
        }
    }

    /**
     * Dump all unknown properties in here when this Element gets deserialized
     * 
     * @param key   the key
     * @param value the value
     */
    @JsonAnySetter()
    public void dump(String key, String value) {
        // NOTE dump all unknown xml properties in this hole
    }

    /**
     * Clear the children of this element
     */
    public void clearChildren() {
        synchronized (children) {
            children.clear();
        }
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
