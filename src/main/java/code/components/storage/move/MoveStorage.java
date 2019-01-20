package code.components.storage.move;

import code.components.Storage;
import code.components.ComponentType;

/**
 * Created by theo on 3/06/17.
 */
public class MoveStorage extends Storage {
    private double speed;
    private double acceleration;
    private double turnRate;

    public MoveStorage(double speed, double acceleration, double turnRate) {
        super(ComponentType.MOVE);
        this.speed = speed;
        this.acceleration = acceleration;
        this.turnRate = turnRate;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double maxSpeed) {
        this.speed = maxSpeed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acc) {
        this.acceleration = acc;
    }

    public double getTurnRate() {
        return turnRate;
    }

    public void setTurnRate(double turnRate) {
        this.turnRate = turnRate;
    }

    public MoveStorage copy() {
        return new MoveStorage(speed, acceleration, turnRate);
    }
}
