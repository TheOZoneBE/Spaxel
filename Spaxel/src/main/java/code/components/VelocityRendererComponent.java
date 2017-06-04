package code.components;

import code.engine.Engine;
import code.engine.NEntity;
import code.graphics.MasterBuffer;
import code.math.VectorF;

/**
 * Created by theo on 4/06/17.
 */
public class VelocityRendererComponent extends RendererComponent {
    public void render(VectorF offset, NEntity entity, MasterBuffer buffer){
        PositionComponent pc = (PositionComponent)entity.getComponent(ComponentType.POSITION);
        VelocityComponent vc = (VelocityComponent)entity.getComponent(ComponentType.VELOCITY);
        SpriteComponent sc = (SpriteComponent)entity.getComponent(ComponentType.SPRITE);
        VectorF pos = pc.getCoord().sum(offset).sum(vc.getVelocity().multiplicate(Engine.getEngine().getUpdateTime()));
        float rot = pc.getRot() + vc.getDeltaRot()*Engine.getEngine().getUpdateTime();
        sc.getSprite().renderSprite(pos, sc.getScale(),rot,1, buffer);
    }
}
