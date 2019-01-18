package code.components.hit;

import code.components.Component;
import code.components.ComponentType;
import code.components.particle.ParticleComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.entity.Entity;
import code.util.SpriteDataUtil;

/**
 * Created by theo on 18/06/17.
 */
public class BasicLaserHitComponent extends HitComponent {
    private static final int PARTICLE_SIZE = 3;

    public BasicLaserHitComponent(int damage) {
        super(HitType.BASIC_LASER, damage);
    }

    public void hit(Entity entity, Entity victim) {
        dealDamage(victim);

        SpriteComponent sc = (SpriteComponent) victim.getComponent(ComponentType.SPRITE);
        addParticleSpawner(entity,
                new ParticleComponent(
                        SpriteDataUtil.getRandomPart(sc.getSprite(), PARTICLE_SIZE, PARTICLE_SIZE),
                        sc.getScale()),
                "laser_hit_particle_spawner_industry");

        entity.destroy();
    }

    public Component copy() {
        return new BasicLaserHitComponent(damage);
    }
}
