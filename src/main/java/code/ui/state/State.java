package code.ui.state;

/**
 * Represents the internal state of an ui Element.
 */
public class State {
    private boolean hover;
    private boolean click;

    public State() {
        super();
    }

    /**
     * @return the hover
     */
    public boolean isHover() {
        return hover;
    }

    /**
     * @param hover the hover to set
     */
    public void setHover(boolean hover) {
        this.hover = hover;
    }

    /**
     * @return the click
     */
    public boolean isClick() {
        return click;
    }

    /**
     * @param click the click to set
     */
    public void setClick(boolean click) {
        this.click = click;
    }


}
