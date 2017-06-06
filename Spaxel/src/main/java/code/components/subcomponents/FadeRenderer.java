package code.components.subcomponents;

import code.components.AgeComponent;
import code.components.ComponentType;
import code.engine.NEntity;
import code.graphics.RenderData;

/**
 * Created by theo on 5/06/17.
 */
public class FadeRenderer extends Renderer {
    public void apply(RenderData data, NEntity entity){
        AgeComponent ac = (AgeComponent)entity.getComponent(ComponentType.AGE);
        float[] sinCos = data.getSinCos();
        sinCos[2] = (float)ac.getLife()/ac.getMaxLife();
        data.setSinCos(sinCos);
    }
}
