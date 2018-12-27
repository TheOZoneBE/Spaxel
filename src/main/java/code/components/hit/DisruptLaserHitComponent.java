package code.components.hit;

import code.components.ComponentType;
import code.components.particle.ParticleComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.util.SpriteDataUtil;

/**
 * Created by theod on 28-6-2017.
 */
public class DisruptLaserHitComponent extends HitComponent {
    private static final int PARTICLE_SIZE = 3;

    public DisruptLaserHitComponent(int damage) {
        super(HitType.DISRUPT_LASER, damage);
    }

    public void hit(NEntity entity, NEntity victim) {
        dealDamage(victim);

        SpriteComponent sc = (SpriteComponent) victim.getComponent(ComponentType.SPRITE);
        addParticleSpawner(entity, victim,
                new ParticleComponent(SpriteDataUtil.getRandomPart(sc.getSprite(), PARTICLE_SIZE, PARTICLE_SIZE),
                        sc.getScale()),
                "laser_hit_particle_spawner_industry");

        addEffect(victim, "disable_move_effect_industry");

        Engine.getEngine().getNEntityStream().removeEntity(entity);
    }
}
