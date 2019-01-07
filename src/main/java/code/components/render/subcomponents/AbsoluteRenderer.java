package code.components.render.subcomponents;

import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.engine.NEntity;
import code.graphics.buffer.RenderJob;

/**
 * Created by theo on 5/01/18.
 */
public class AbsoluteRenderer extends Renderer {
    public void apply(RenderJob data, NEntity entity) {
        PositionComponent pc = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        SpriteComponent sc = (SpriteComponent) entity.getComponent(ComponentType.SPRITE);

        // TODO scaling should not be in here
        data.applyTranslation(pc.getCoord());
        data.applyScale(sc.getScale());
        data.applyRot(pc.getRot());
    }
}
