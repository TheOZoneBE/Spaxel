package code.components.move;

import code.components.Component;
import code.components.ComponentType;

/**
 * Created by theo on 3/06/17.
 */
public class MoveComponent extends Component {
    private double maxSpeed;
    private double acc;
    private double turnRate;

    public MoveComponent(double maxSpeed, double acc, double turnRate) {
        super(ComponentType.MOVE);
        this.maxSpeed = maxSpeed;
        this.acc = acc;
        this.turnRate = turnRate;
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

    public Component copy(){
        return new MoveComponent(maxSpeed, acc, turnRate);
    }
}
