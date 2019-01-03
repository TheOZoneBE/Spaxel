package code.components.render.subcomponents;

import code.components.ComponentType;
import code.components.link.LinkComponent;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.components.velocity.VelocityComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.graphics.RenderData;
import code.math.VectorD;

/**
 * Created by theo on 8/07/17.
 */
public class LinkLinkVelocityRenderer extends Renderer {
    public void apply(RenderData data, NEntity entity) {
        NEntity link = ((LinkComponent) entity.getComponent(ComponentType.LINK)).getLink();
        NEntity linkLink = ((LinkComponent) link.getComponent(ComponentType.LINK)).getLink();
        PositionComponent pc = (PositionComponent) linkLink.getComponent(ComponentType.POSITION);
        VelocityComponent vc = (VelocityComponent) linkLink.getComponent(ComponentType.VELOCITY);
        SpriteComponent sc = (SpriteComponent) entity.getComponent(ComponentType.SPRITE);
        VectorD pos = pc.getCoord().sum(Engine.get().getScreenOffset())
                .sum(vc.getVelocity().multiplicate(Engine.get().getUpdateTime()));
        double rot = pc.getRot() + vc.getDeltaRot() * Engine.get().getUpdateTime();

        data.setPos(pos);

        data.setScale(sc.getSprite().getDim().multiplicate(sc.getScale()));
        data.setRot(rot);
    }
}
