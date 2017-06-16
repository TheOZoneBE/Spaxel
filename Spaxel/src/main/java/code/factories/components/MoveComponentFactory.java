package code.factories.components;

import code.components.Component;
import code.components.move.MoveComponent;

/**
 * Created by theo on 3/06/17.
 */
public class MoveComponentFactory extends ComponentFactory {
    private float maxSpeed;
    private float acc;
    private float turnRate;

    public MoveComponentFactory(){

    }

    public Component make(){
        return new MoveComponent(maxSpeed, acc, turnRate);
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getAcc() {
        return acc;
    }

    public void setAcc(float acc) {
        this.acc = acc;
    }

    public float getTurnRate() {
        return turnRate;
    }

    public void setTurnRate(float turnRate) {
        this.turnRate = turnRate;
    }
}
