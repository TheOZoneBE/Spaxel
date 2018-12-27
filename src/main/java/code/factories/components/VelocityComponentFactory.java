package code.factories.components;

import code.components.Component;
import code.components.velocity.VelocityComponent;
import code.math.VectorF;

/**
 * Created by theo on 3/06/17.
 */
public class VelocityComponentFactory extends ComponentFactory {
    private VectorF velocity;
    private float deltaRot;

    public VelocityComponentFactory() {
        super();
    }

    public Component make() {
        return new VelocityComponent(velocity.clone(), deltaRot);
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
