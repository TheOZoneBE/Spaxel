package code.components.position;

import code.components.Component;
import code.components.ComponentType;
import code.math.VectorD;
import code.Constants;

/**
 * Created by theo on 3/06/17.
 */
public class PositionComponent extends Component {
    private VectorD coord;
    private double rot;

    private PositionComponent() {
        super(ComponentType.POSITION);
    }

    public PositionComponent(VectorD coord, double rot) {
        super(ComponentType.POSITION);
        this.coord = coord;
        this.rot = rot;
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
        if (rot < 0) {
            this.rot = rot + Constants.FULL_CIRCLE;
        } else {
            this.rot = rot % (Constants.FULL_CIRCLE);
        }

    }

    public Component copy() {
        return new PositionComponent(coord.copy(), rot);
    }
}
