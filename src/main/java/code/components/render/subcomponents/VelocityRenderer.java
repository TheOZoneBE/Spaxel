package code.components.render.subcomponents;

import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
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
        PositionComponent pc = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        ChangeStorage vc = (ChangeStorage) entity.getComponent(ComponentType.CHANGE);
        SpriteComponent sc = (SpriteComponent) entity.getComponent(ComponentType.SPRITE);
        VectorD pos = pc.getCoord().sum(Engine.get().getGameState().getScreenOffset()).sum(
                vc.getPositionChange().multiplicate(Engine.get().getGameState().getUpdateTime()));
        double rot =
                pc.getRot() + vc.getRotationChange() * Engine.get().getGameState().getUpdateTime();

        data.applyTranslation(pos);
        // TODO scaling should not be in here
        data.applyScale(sc.getScale());

        data.applyRot(rot);
    }
}
