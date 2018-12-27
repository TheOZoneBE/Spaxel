package code.components.position;

import code.components.Component;
import code.components.ComponentType;
import code.math.VectorF;
import code.Constants;

/**
 * Created by theo on 3/06/17.
 */
public class PositionComponent extends Component {
    private VectorF coord;
    private float rot;

    private PositionComponent() {
        super(ComponentType.POSITION);
    }

    public PositionComponent(VectorF coord, float rot) {
        super(ComponentType.POSITION);
        this.coord = coord;
        this.rot = rot;
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
        if (rot < 0) {
            this.rot = (float) (rot + Constants.FULL_CIRCLE);
        } else {
            this.rot = (float) (rot % (Constants.FULL_CIRCLE));
        }

    }

    public Component clone() {
        return new PositionComponent(coord.clone(), rot);
    }
}
