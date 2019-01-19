package code.components.render.subcomponents;

import code.components.storage.age.AgeStorage;
import code.components.ComponentType;
import code.entity.Entity;
import code.graphics.buffer.RenderJob;

/**
 * Created by theo on 5/06/17.
 */
public class FadeRenderer extends Renderer {
    public void apply(RenderJob data, Entity entity) {
        AgeStorage ac = (AgeStorage) entity.getComponent(ComponentType.AGE);

        data.applyAlpha((double) ac.getLife() / ac.getMaxLife());
    }
}
