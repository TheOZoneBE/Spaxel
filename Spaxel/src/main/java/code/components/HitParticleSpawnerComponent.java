package code.components;

import code.engine.Engine;
import code.engine.NEntity;
import code.factories.entities.HitParticleIndustry;
import code.math.VectorF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theo on 6/06/17.
 */
public class HitParticleSpawnerComponent extends SpawnerComponent {
    private float maxDeltaRot;
    private float maxSpeed;
    private int maxLife;

    public HitParticleSpawnerComponent(int rate, float maxDeltaRot, float maxSpeed, int maxLife) {
        super(SpawnerType.HITPARTICLE, rate);
        this.maxDeltaRot = maxDeltaRot;
        this.maxSpeed = maxSpeed;
        this.maxLife = maxLife;
    }

    public List<NEntity> spawn(NEntity entity){
        List<NEntity> temp = new ArrayList<>();
        HitParticleIndustry hpi = (HitParticleIndustry) Engine.getEngine().getIndustryMap().get("hit_particle_industry");
        PositionComponent pc = (PositionComponent)entity.getComponent(ComponentType.POSITION);
        SpriteComponent sc = (SpriteComponent)entity.getComponent(ComponentType.SPRITE);
        for (int i = 0; i < rate; i++) {
            int life = rand.nextInt(maxLife);
            float dir = rand.nextFloat() * 2 * (float)Math.PI;
            float speed = rand.nextFloat() * maxSpeed;
            float deltaRot = (rand.nextFloat() - .5f) * maxDeltaRot;
            float dx = (float)Math.sin(dir) * speed;
            float dy = (float)Math.cos(dir) * speed;
            temp.add(hpi.produce(pc.getCoord(), pc.getRot(), life, life, new VectorF(dx, dy), deltaRot, sc.getSprite(), sc.getScale()));
        }
        return temp;
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
