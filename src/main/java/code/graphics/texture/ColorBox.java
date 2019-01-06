package code.graphics.texture;

import code.math.VectorD;
import com.fasterxml.jackson.annotation.JsonSetter;
import code.graphics.buffer.RenderData;


public class ColorBox extends Renderable {
    private static final int HEX_SIZE = 16;

    private int color;

    public ColorBox() {
        super();
    }

    public ColorBox(VectorD dim, int color) {
        super(dim);
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    @JsonSetter("color")
    public void setColorValue(String colorValue) {
        color = Integer.parseUnsignedInt(colorValue, HEX_SIZE);
    }

    public void apply(RenderData data) {
        data.applyScale(dim);
        data.setColor(color);
    }
}
