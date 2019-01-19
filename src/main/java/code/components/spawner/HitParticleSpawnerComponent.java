package code.components.spawner;

import code.components.ComponentType;
import code.components.Component;
import code.components.storage.age.AgeStorage;
import code.components.particle.ParticleComponent;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.components.velocity.VelocityComponent;
import code.engine.Resources;
import code.entity.Entity;
import code.factories.entities.HitParticleIndustry;
import code.math.VectorD;
import code.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by theo on 6/06/17.
 */
public class HitParticleSpawnerComponent extends SpawnerComponent {
    private double maxDeltaRot;
    private double maxSpeed;
    private int maxLife;

    public HitParticleSpawnerComponent(int rate, double maxDeltaRot, double maxSpeed, int maxLife) {
        super(SpawnerType.HITPARTICLE, rate);
        this.maxDeltaRot = maxDeltaRot;
        this.maxSpeed = maxSpeed;
        this.maxLife = maxLife;
    }

    public List<Entity> spawn(Entity entity) {
        List<Entity> temp = new ArrayList<>();
        HitParticleIndustry hpi =
                (HitParticleIndustry) Resources.get().getIndustryMap().get("hit_particle_industry");
        PositionComponent pc = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        ParticleComponent pac = (ParticleComponent) entity.getComponent(ComponentType.PARTICLE);
        for (int i = 0; i < rate; i++) {
            int life = rand.nextInt(maxLife);
            double dir = rand.nextDouble(Constants.FULL_CIRCLE);
            double speed = rand.nextDouble(maxSpeed);
            double deltaRot = rand.between(-maxDeltaRot, maxDeltaRot);
            double dx = Math.sin(dir) * speed;
            double dy = Math.cos(dir) * speed;
            temp.add(hpi.produce((PositionComponent) pc.copy(), new AgeStorage(life, life),
                    new VelocityComponent(new VectorD(dx, dy), deltaRot),
                    new SpriteComponent(pac.getParticle(), pac.getScale())));
        }
        return temp;
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

    public Component copy() {
        return new HitParticleSpawnerComponent(rate, maxDeltaRot, maxSpeed, maxLife);
    }
}
