package code.components.render.subcomponents;

import code.components.ComponentType;
import code.components.item.ShieldItemComponent;
import code.entity.Entity;
import code.graphics.buffer.RenderJob;

/**
 * Created by theod on 10-7-2017.
 */
public class ShieldRenderer extends Renderer {
    private static final int ALPHA_REDUCER = 2;
    private static final double ALPHA_OFFSET = 0.15;

    public void apply(RenderJob data, Entity entity) {
        Entity link = entity.getParent();
        ShieldItemComponent shc = (ShieldItemComponent) link.getComponent(ComponentType.ITEM);

        data.applyAlpha(
                ((double) shc.getCapacity() / shc.getMaxCapacity()) / ALPHA_REDUCER + ALPHA_OFFSET);
    }
}
