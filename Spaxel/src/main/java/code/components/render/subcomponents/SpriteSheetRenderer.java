package code.components.render.subcomponents;

import code.components.ComponentType;
import code.components.sprite.SpriteComponent;
import code.engine.NEntity;
import code.graphics.RenderData;

/**
 * Created by theo on 5/06/17.
 */
public class SpriteSheetRenderer extends Renderer {
    public void apply(RenderData data, NEntity entity){
        SpriteComponent sc = (SpriteComponent)entity.getComponent(ComponentType.SPRITE);
        data.setTexOffset(sc.getSprite().getSpriteProperties());
        data.setSpriteSheetID(sc.getSprite().getSpritesheetID());
    }
}
