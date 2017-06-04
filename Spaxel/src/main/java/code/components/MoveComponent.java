package code.components;

/**
 * Created by theo on 3/06/17.
 */
public class MoveComponent extends Component {
    private float maxSpeed;
    private float acc;
    private float turnRate;

    public MoveComponent(float maxSpeed, float acc, float turnRate) {
        super(ComponentType.MOVE);
        this.maxSpeed = maxSpeed;
        this.acc = acc;
        this.turnRate = turnRate;
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
