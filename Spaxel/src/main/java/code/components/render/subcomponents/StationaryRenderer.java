package code.components.render.subcomponents;

import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.graphics.RenderData;
import code.math.VectorF;

/**
 * Created by theo on 5/06/17.
 */
public class StationaryRenderer extends Renderer {
    public void apply(RenderData data, NEntity entity){
        PositionComponent pc = (PositionComponent)entity.getComponent(ComponentType.POSITION);
        SpriteComponent sc = (SpriteComponent)entity.getComponent(ComponentType.SPRITE);
        VectorF pos = pc.getCoord().sum(Engine.getEngine().getScreenOffset());
        data.setTrSc(new float[]{pos.getValue(0), pos.getValue(1), sc.getSprite().getWidth()*sc.getScale(), sc.getSprite().getHeight()*sc.getScale()});
        float[] sinCos = data.getSinCos();
        sinCos[0] = (float)Math.sin(pc.getRot());
        sinCos[1] = (float)Math.cos(pc.getRot());
        data.setSinCos(sinCos);
    }
}
