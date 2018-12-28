package code.components.hit;

import code.components.ComponentType;
import code.components.Component;
import code.components.particle.ParticleComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.util.SpriteDataUtil;

/**
 * Created by theod on 28-6-2017.
 */
public class SlowingLaserHitComponent extends HitComponent {
    private static final int PARTICLE_SIZE = 3;

    public SlowingLaserHitComponent(int damage) {
        super(HitType.SLOWING_LASER, damage);
    }

    public void hit(NEntity entity, NEntity victim) {
        dealDamage(victim);

        SpriteComponent sc = (SpriteComponent) victim.getComponent(ComponentType.SPRITE);
        addParticleSpawner(entity, 
                new ParticleComponent(SpriteDataUtil.getRandomPart(sc.getSprite(), PARTICLE_SIZE, PARTICLE_SIZE),
                        sc.getScale()),
                "laser_hit_particle_spawner_industry");

        addEffect(victim, "slow_effect_industry");

        Engine.getEngine().getNEntityStream().removeEntity(entity);
    }

    public Component copy() {
        return new SlowingLaserHitComponent(damage);
    }
}
