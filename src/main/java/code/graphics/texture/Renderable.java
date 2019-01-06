package code.graphics.texture;

import code.graphics.buffer.RenderData;
import code.math.VectorD;

public abstract class Renderable {
    protected VectorD dim;
    protected String name;

    public Renderable() {
        super();
    }

    public Renderable(VectorD dim) {
        this.dim = dim;
    }

    /**
     * @param dim the dim to set
     */
    public void setDim(VectorD dim) {
        this.dim = dim;
    }

    public VectorD getDim() {
        return dim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void apply(RenderData data);
}
