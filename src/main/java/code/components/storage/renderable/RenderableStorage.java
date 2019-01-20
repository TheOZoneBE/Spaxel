package code.components.storage.renderable;

import code.components.ComponentType;
import code.components.Storage;
import code.graphics.texture.Renderable;

public class RenderableStorage extends Storage {
    private Renderable renderable;

    public RenderableStorage() {
        super(ComponentType.RENDERABLE);
    }

    public RenderableStorage(Renderable renderable) {
        super(ComponentType.RENDERABLE);
        this.renderable = renderable;
    }

    /**
     * @return the renderable
     */
    public Renderable getRenderable() {
        return renderable;
    }

    /**
     * @param renderable the renderable to set
     */
    public void setRenderable(Renderable renderable) {
        this.renderable = renderable;
    }

    public RenderableStorage copy() {
        return new RenderableStorage(renderable);
    }
}
