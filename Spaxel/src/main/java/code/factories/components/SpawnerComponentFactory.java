package code.factories.components;

import code.components.Component;
import code.components.HitParticleSpawnerComponent;
import code.components.SpawnerComponent;
import code.components.SpawnerType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by theo on 5/06/17.
 */
public class SpawnerComponentFactory extends ComponentFactory {
    protected int rate;
    private SpawnerType subType;
    private float maxDeltaRot;
    private float maxSpeed;
    private int maxLife;

    public Component make(){
        if(subType == SpawnerType.HITPARTICLE){
            return new HitParticleSpawnerComponent(rate, maxDeltaRot, maxSpeed, maxLife);
        }
        return null;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public SpawnerType getSubType() {
        return subType;
    }

    public void setSubType(SpawnerType subType) {
        this.subType = subType;
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
