package code.components.behaviour.render.renderers;

import code.components.ComponentType;
import code.components.storage.renderable.RenderableStorage;
import code.entity.Entity;
import code.graphics.buffer.RenderJob;

/**
 * Created by theo on 5/06/17.
 */
public class SpriteSheetRenderer extends Renderer {
    public void apply(RenderJob data, Entity entity) {
        RenderableStorage sc = (RenderableStorage) entity.getComponent(ComponentType.RENDERABLE);

        data.setRenderable(sc.getRenderable());
    }
}
