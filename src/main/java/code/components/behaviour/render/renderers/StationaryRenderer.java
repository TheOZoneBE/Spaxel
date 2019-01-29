package code.components.behaviour.render.renderers;

import code.components.ComponentType;
import code.components.storage.transformation.TransformationStorage;
import code.engine.Engine;
import code.entity.Entity;
import code.graphics.buffer.RenderJob;
import code.math.VectorD;

/**
 * Created by theo on 5/06/17.
 */
public class StationaryRenderer extends Renderer {
    public void apply(RenderJob data, Entity entity) {
        TransformationStorage pc =
                (TransformationStorage) entity.getComponent(ComponentType.TRANSFORMATION);
        VectorD pos = pc.getPosition().sum(Engine.get().getGameState().getScreenOffset());

        data.applyTranslation(pos);
        data.applyScale(pc.getScale());
        data.applyRot(pc.getRotation());
    }
}
