package code.components.render.subcomponents;

import code.components.ComponentType;
import code.components.age.AgeComponent;
import code.engine.NEntity;
import code.graphics.buffer.RenderJob;
import code.math.VectorD;

/**
 * Created by theod on 25-9-2017.
 */
public class ShipFragmentRenderer extends Renderer {
    private static final double FACTOR_MULT = 2.0;

    public void apply(RenderJob data, NEntity entity) {
        AgeComponent ac = (AgeComponent) entity.getComponent(ComponentType.AGE);

        double factor = ac.getLife() * FACTOR_MULT / ac.getMaxLife();

        data.applyScale(new VectorD(factor, factor));
    }

}
