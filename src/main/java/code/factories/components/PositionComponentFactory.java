package code.factories.components;

import code.components.Component;
import code.components.position.PositionComponent;
import code.math.VectorD;

/**
 * Created by theo on 3/06/17.
 */
public class PositionComponentFactory extends ComponentFactory {
    private VectorD coord;
    private double rot;

    public PositionComponentFactory() {
        super();
    }

    public Component make() {
        return new PositionComponent(coord.clone(), rot);
    }

    public VectorD getCoord() {
        return coord;
    }

    public void setCoord(VectorD coord) {
        this.coord = coord;
    }

    public double getRot() {
        return rot;
    }

    public void setRot(double rot) {
        this.rot = rot;
    }
}
