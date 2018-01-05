package code.components.render.subcomponents;

import code.engine.NEntity;
import code.graphics.RenderData;

/**
 * Created by theo on 5/01/18.
 */
public class MarkerRenderer extends Renderer {
    public void apply(RenderData data, NEntity entity){
        data.setAlpha(0.33f);
    }
}
