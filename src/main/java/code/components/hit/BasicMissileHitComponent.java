package code.components.hit;

import code.components.Component;
import code.components.ComponentType;
import code.components.particle.ParticleComponent;
import code.components.sprite.SpriteComponent;
import code.entity.Entity;
import code.util.SpriteDataUtil;

/**
 * Created by theod on 2-7-2017.
 */
public class BasicMissileHitComponent extends HitComponent {
    private static final int PARTICLE_SIZE = 4;

    public BasicMissileHitComponent(int damage) {
        super(HitType.BASIC_MISSILE, damage);
    }

    public void hit(Entity entity, Entity victim) {
        dealDamage(victim);

        SpriteComponent sc = (SpriteComponent) victim.getComponent(ComponentType.SPRITE);
        addParticleSpawner(entity,
                new ParticleComponent(
                        SpriteDataUtil.getRandomPart(sc.getSprite(), PARTICLE_SIZE, PARTICLE_SIZE),
                        sc.getScale()),
                "missile_hit_particle_spawner_industry");

        entity.destroy();
    }

    public Component copy() {
        return new BasicMissileHitComponent(damage);
    }
}
