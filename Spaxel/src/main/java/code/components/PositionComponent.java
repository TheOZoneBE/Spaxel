package code.components;

import code.math.VectorF;

/**
 * Created by theo on 3/06/17.
 */
public class PositionComponent extends Component{
    private VectorF coord;
    private float rot;

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
        this.rot = rot;
    }
}
