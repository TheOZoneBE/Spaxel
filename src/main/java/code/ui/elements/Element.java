package code.ui.elements;

import code.ui.styles.Style;
import java.util.List;
import code.ui.logic.Logic;
import code.ui.state.State;

public class Element {
    private List<StringStyle> classStyles;
    private List<StringStyle> idStyles;
    private List<Element> children;
    private List<Logic> logic;
    private Style style;
    private State state;

    private void render(MasterBuffer buffer) {

    }

    private void update() {
        for (Logic l : logic) {
            l.update(this);
        }
        for (Element element : children) {
            element.update();
        }
    }

    private void refreshStyle(Style style) {
        for (StringStyle stl : classStyles) {
            style = style.merge(stl.style);
        }
        for (StringStyle stl : idStyles) {
            style = style.merge(stl.style);
        }
        this.style = style;
        for (Element element : children) {
            element.refreshStyle(style);
        }
    }

    private static class StringStyle {
        private String key;
        private Style style;

        public StringStyle(String key, Style style) {
            this.key = key;
            this.style = style;
        }
    }

    public Style getStyle() {
        return style;
    }

    public State getState() {
        return state;
    }
}
