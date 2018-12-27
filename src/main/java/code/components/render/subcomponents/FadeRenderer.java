package code.components.render.subcomponents;

import code.components.age.AgeComponent;
import code.components.ComponentType;
import code.engine.NEntity;
import code.graphics.RenderData;

/**
 * Created by theo on 5/06/17.
 */
public class FadeRenderer extends Renderer {
    public void apply(RenderData data, NEntity entity) {
        AgeComponent ac = (AgeComponent) entity.getComponent(ComponentType.AGE);

        data.setAlpha(ac.getLife() / ac.getMaxLife());
    }
}
