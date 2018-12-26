package code.components.render.subcomponents;

import code.components.ComponentType;
import code.components.age.AgeComponent;
import code.components.sprite.SpriteComponent;
import code.engine.NEntity;
import code.graphics.RenderData;

/**
 * Created by theod on 25-9-2017.
 */
public class ShipFragmentRenderer extends Renderer{

    public void apply(RenderData data, NEntity entity){
        AgeComponent ac = (AgeComponent)entity.getComponent(ComponentType.AGE);
        SpriteComponent sc = (SpriteComponent)entity.getComponent(ComponentType.SPRITE);

        float factor = (float)ac.getLife()*2/ac.getMaxLife();

        data.setXScale(sc.getSprite().getWidth()*sc.getScale()*factor);
        data.setYScale(sc.getSprite().getHeight()*sc.getScale()*factor);
    }


}
