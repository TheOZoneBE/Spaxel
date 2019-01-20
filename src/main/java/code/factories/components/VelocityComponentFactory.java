package code.factories.components;

import code.components.Component;
import code.components.storage.change.ChangeStorage;
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

    @Override
    public Component make() {
        return new ChangeStorage(velocity.copy(), deltaRot, 0);
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
