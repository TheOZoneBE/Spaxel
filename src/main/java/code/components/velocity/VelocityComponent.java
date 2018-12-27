package code.components.velocity;

import code.components.Component;
import code.components.ComponentType;
import code.math.VectorD;

/**
 * Created by theo on 3/06/17.
 */
public class VelocityComponent extends Component {
    private VectorD velocity;
    private double deltaRot;

    public VelocityComponent(VectorD velocity, double deltaRot) {
        super(ComponentType.VELOCITY);
        this.velocity = velocity;
        this.deltaRot = deltaRot;
    }

    public VectorD getVelocity() {
        return velocity;
    }

    public void setVelocity(VectorD velocity) {
        this.velocity = velocity;
    }

    public double getDeltaRot() {
        return deltaRot;
    }

    public void setDeltaRot(double deltaRot) {
        this.deltaRot = deltaRot;
    }

    public Component copy() {
        return new VelocityComponent(velocity.copy(), deltaRot);
    }
}
