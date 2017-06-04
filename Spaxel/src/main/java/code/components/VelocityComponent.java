package code.components;

import code.math.VectorF;

/**
 * Created by theo on 3/06/17.
 */
public class VelocityComponent extends Component {
    private VectorF velocity;
    private float deltaRot;

    public VelocityComponent(VectorF velocity, float deltaRot) {
        super(ComponentType.VELOCITY);
        this.velocity = velocity;
        this.deltaRot = deltaRot;
    }

    public VectorF getVelocity() {
        return velocity;
    }

    public void setVelocity(VectorF velocity) {
        this.velocity = velocity;
    }

    public float getDeltaRot() {
        return deltaRot;
    }

    public void setDeltaRot(float deltaRot) {
        this.deltaRot = deltaRot;
    }
}
