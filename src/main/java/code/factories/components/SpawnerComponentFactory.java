package code.factories.components;

import code.components.Component;
import code.components.spawner.HitParticleSpawnerComponent;
import code.components.spawner.ShipFragmentSpawnerComponent;
import code.components.spawner.SpawnerType;
import code.components.spawner.TrailSegmentSpawnerComponent;

/**
 * Created by theo on 5/06/17.
 */
public class SpawnerComponentFactory extends ComponentFactory {
    protected int rate;
    private SpawnerType spawnerType;
    private double maxDeltaRot;
    private double maxSpeed;
    private int maxLife;

    public SpawnerComponentFactory() {
        super();
    }

    @Override
    public Component make() {
        Component result = null;
        switch (spawnerType) {
        case HITPARTICLE:
            result = new HitParticleSpawnerComponent(rate, maxDeltaRot, maxSpeed, maxLife);
            break;
        case TRAILSEGMENT:
            result = new TrailSegmentSpawnerComponent(rate);
            break;
        case SHIP_FRAGMENT:
            result = new ShipFragmentSpawnerComponent(rate);
            break;
        default:
            break;
        }
        return result;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public SpawnerType getSpawnerType() {
        return spawnerType;
    }

    public void setSpawnerType(SpawnerType spawnerType) {
        this.spawnerType = spawnerType;
    }

    public double getMaxDeltaRot() {
        return maxDeltaRot;
    }

    public void setMaxDeltaRot(double maxDeltaRot) {
        this.maxDeltaRot = maxDeltaRot;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }
}
