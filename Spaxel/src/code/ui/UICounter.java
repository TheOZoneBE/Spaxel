package code.ui;

import code.graphics.RenderBuffer;

import java.awt.*;

/**
 * Created by theo on 28-5-2016.
 */
public class UICounter extends UIElement {
    private Label label;
    private int counter;

    public UICounter(int x, int y, Label label) {
        super(x, y, null);
        this.label = label;
        counter = 0;
    }

    public void setCounter(int counter){
        this.counter = counter;
    }

    public void addToCounter(int add){
        counter += add;
    }

    public void render(RenderBuffer  render){
        label.render(render);
    }

    public void update(){
        label.setText(String.valueOf(counter));
    }

}
