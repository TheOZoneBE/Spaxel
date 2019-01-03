package code.components.render.subcomponents;

import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.graphics.RenderData;
import code.math.VectorD;

/**
 * Created by theo on 5/06/17.
 */
public class StationaryRenderer extends Renderer {
    public void apply(RenderData data, NEntity entity) {
        PositionComponent pc = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        SpriteComponent sc = (SpriteComponent) entity.getComponent(ComponentType.SPRITE);
        VectorD pos = pc.getCoord().sum(Engine.get().getScreenOffset());

        data.setPos(pos);

        data.setScale(sc.getSprite().getDim().multiplicate(sc.getScale()));
        data.setRot(pc.getRot());
    }
}
