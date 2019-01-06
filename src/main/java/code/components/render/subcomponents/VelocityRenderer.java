package code.components.render.subcomponents;

import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.components.velocity.VelocityComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.graphics.buffer.RenderData;
import code.math.VectorD;

/**
 * Created by theo on 5/06/17.
 */
public class VelocityRenderer extends Renderer {
    public void apply(RenderData data, NEntity entity) {
        PositionComponent pc = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        VelocityComponent vc = (VelocityComponent) entity.getComponent(ComponentType.VELOCITY);
        SpriteComponent sc = (SpriteComponent) entity.getComponent(ComponentType.SPRITE);
        VectorD pos = pc.getCoord().sum(Engine.get().getGameState().getScreenOffset())
                .sum(vc.getVelocity().multiplicate(Engine.get().getGameState().getUpdateTime()));
        double rot = pc.getRot() + vc.getDeltaRot() * Engine.get().getGameState().getUpdateTime();

        data.applyTranslation(pos);
        // TODO scaling should not be in here
        data.applyScale(sc.getScale());

        data.applyRot(rot);
    }
}
