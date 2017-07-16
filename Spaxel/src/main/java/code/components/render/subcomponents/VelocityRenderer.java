package code.components.render.subcomponents;

import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.components.velocity.VelocityComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.graphics.RenderData;
import code.math.VectorF;

/**
 * Created by theo on 5/06/17.
 */
public class VelocityRenderer extends Renderer {
    public void apply(RenderData data, NEntity entity){
        PositionComponent pc = (PositionComponent)entity.getComponent(ComponentType.POSITION);
        VelocityComponent vc = (VelocityComponent)entity.getComponent(ComponentType.VELOCITY);
        SpriteComponent sc = (SpriteComponent)entity.getComponent(ComponentType.SPRITE);
        VectorF pos = pc.getCoord().sum(Engine.getEngine().getScreenOffset()).sum(vc.getVelocity().multiplicate(Engine.getEngine().getUpdateTime()));
        float rot = pc.getRot() + vc.getDeltaRot()*Engine.getEngine().getUpdateTime();

        data.setPos(pos);
        data.setXScale(sc.getSprite().getWidth()*sc.getScale());
        data.setYScale(sc.getSprite().getWidth()*sc.getScale());
        data.setRot(rot);
    }
}
