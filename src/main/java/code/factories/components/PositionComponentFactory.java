package code.factories.components;

import code.components.Component;
import code.components.position.PositionComponent;
import code.math.VectorF;

/**
 * Created by theo on 3/06/17.
 */
public class PositionComponentFactory extends ComponentFactory {
    private VectorF coord;
    private float rot;

    public PositionComponentFactory() {
        super();
    }

    public Component make() {
        return new PositionComponent(coord.clone(), rot);
    }

    public VectorF getCoord() {
        return coord;
    }

    public void setCoord(VectorF coord) {
        this.coord = coord;
    }

    public float getRot() {
        return rot;
    }

    public void setRot(float rot) {
        this.rot = rot;
    }
}
