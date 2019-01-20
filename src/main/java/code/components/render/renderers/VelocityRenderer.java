package code.components.render.renderers;

import code.components.ComponentType;
import code.components.storage.transformation.TransformationStorage;
import code.components.storage.change.ChangeStorage;
import code.engine.Engine;
import code.entity.Entity;
import code.graphics.buffer.RenderJob;
import code.math.VectorD;

/**
 * Created by theo on 5/06/17.
 */
public class VelocityRenderer extends Renderer {
        public void apply(RenderJob data, Entity entity) {
                TransformationStorage pc = (TransformationStorage) entity
                                .getComponent(ComponentType.TRANSFORMATION);
                ChangeStorage vc = (ChangeStorage) entity.getComponent(ComponentType.CHANGE);
                VectorD pos = pc.getPosition().sum(Engine.get().getGameState().getScreenOffset())
                                .sum(vc.getPositionChange().multiplicate(
                                                Engine.get().getGameState().getUpdateTime()));
                double rot = pc.getRotation() + vc.getRotationationChange()
                                * Engine.get().getGameState().getUpdateTime();

                data.applyTranslation(pos);
                data.applyScale(pc.getScale());
                data.applyRot(rot);
        }
}
