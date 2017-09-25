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
    private float maxDeltaRot;
    private float maxSpeed;
    private int maxLife;

    public Component make(){
        switch (spawnerType){
            case HITPARTICLE:
                return new HitParticleSpawnerComponent(rate, maxDeltaRot, maxSpeed, maxLife);
            case TRAILSEGMENT:
                return new TrailSegmentSpawnerComponent(rate);
            case SHIP_FRAGMENT:
                return new ShipFragmentSpawnerComponent(rate);
        }
        return null;
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

    public float getMaxDeltaRot() {
        return maxDeltaRot;
    }

    public void setMaxDeltaRot(float maxDeltaRot) {
        this.maxDeltaRot = maxDeltaRot;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }
}
