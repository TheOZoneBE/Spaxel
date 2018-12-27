package code.factories.components;

import code.components.Component;
import code.components.velocity.VelocityComponent;
import code.math.VectorD;

/**
 * Created by theo on 3/06/17.
 */
public class VelocityComponentFactory extends ComponentFactory {
    private VectorD velocity;
    private double deltaRot;

    public VelocityComponentFactory() {
        super();
    }

    public Component make() {
        return new VelocityComponent(velocity.clone(), deltaRot);
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
}
