package code.components.behaviour.render.renderers;

import code.components.ComponentType;
import code.components.storage.transformation.TransformationStorage;
import code.entity.Entity;
import code.graphics.buffer.RenderJob;

/**
 * Created by theo on 5/01/18.
 */
public class AbsoluteRenderer extends Renderer {
    public void apply(RenderJob data, Entity entity) {
        TransformationStorage pc =
                (TransformationStorage) entity.getComponent(ComponentType.TRANSFORMATION);

        data.applyTranslation(pc.getPosition());
        data.applyScale(pc.getScale());
        data.applyRot(pc.getRotation());
    }
}
