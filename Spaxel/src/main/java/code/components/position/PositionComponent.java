package code.components.position;

import code.components.Component;
import code.components.ComponentType;
import code.math.VectorF;

/**
 * Created by theo on 3/06/17.
 */
public class PositionComponent extends Component {
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
        if (rot < 0){
            this.rot = (float)(rot + 2*Math.PI);
        }
        else {
            this.rot = (float)(rot % (2*Math.PI));
        }

    }

    public Component clone(){
        return new PositionComponent(coord.clone(), rot);
    }
}
