package code.components.behaviour.render.renderers;

import code.components.ComponentType;
import code.components.storage.transformation.TransformationStorage;
import code.components.storage.change.ChangeStorage;
import code.engine.Engine;
import code.entity.Entity;
import code.graphics.buffer.RenderJob;
import code.math.VectorD;

/**
 * Created by theo on 8/07/17.
 */
public class LinkLinkVelocityRenderer extends Renderer {
        public void apply(RenderJob data, Entity entity) {
                Entity link = entity.getParent();
                Entity linkLink = link.getParent();
                TransformationStorage pc = (TransformationStorage) linkLink
                                .getComponent(ComponentType.TRANSFORMATION);
                ChangeStorage vc = (ChangeStorage) linkLink.getComponent(ComponentType.CHANGE);

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
