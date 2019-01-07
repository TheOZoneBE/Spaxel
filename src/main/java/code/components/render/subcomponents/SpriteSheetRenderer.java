package code.components.render.subcomponents;

import code.components.ComponentType;
import code.components.sprite.SpriteComponent;
import code.entity.Entity;
import code.graphics.buffer.RenderJob;

/**
 * Created by theo on 5/06/17.
 */
public class SpriteSheetRenderer extends Renderer {
    public void apply(RenderJob data, Entity entity) {
        SpriteComponent sc = (SpriteComponent) entity.getComponent(ComponentType.SPRITE);

        data.setRenderable(sc.getSprite());
    }
}
