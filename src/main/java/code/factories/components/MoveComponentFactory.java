package code.factories.components;

import code.components.Component;
import code.components.storage.move.MoveStorage;

/**
 * Created by theo on 3/06/17.
 */
public class MoveComponentFactory extends ComponentFactory {
    private double maxSpeed;
    private double acc;
    private double turnRate;

    public MoveComponentFactory() {
        super();
    }

    @Override
    public Component make() {
        return new MoveStorage(maxSpeed, acc, turnRate);
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getAcc() {
        return acc;
    }

    public void setAcc(double acc) {
        this.acc = acc;
    }

    public double getTurnRate() {
        return turnRate;
    }

    public void setTurnRate(double turnRate) {
        this.turnRate = turnRate;
    }
}
