package code.components.render.subcomponents;

import code.engine.NEntity;
import code.graphics.buffer.RenderJob;

/**
 * Created by theo on 5/01/18.
 */
public class MarkerRenderer extends Renderer {
    private static final double MARKER_ALPHA = 0.33D;

    public void apply(RenderJob data, NEntity entity) {
        data.applyAlpha(MARKER_ALPHA);
    }
}
