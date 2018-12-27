package code.components.hit;

import code.components.ComponentType;
import code.components.particle.ParticleComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.util.SpriteDataUtil;

/**
 * Created by theod on 4-7-2017.
 */
public class ClusterMissileHitComponent extends HitComponent {
    private static final int PARTICLE_SIZE = 4;

    public ClusterMissileHitComponent(int damage) {
        super(HitType.CLUSTER_MISSILE, damage);
    }

    public void hit(NEntity entity, NEntity victim) {
        dealDamage(victim);

        SpriteComponent sc = (SpriteComponent) victim.getComponent(ComponentType.SPRITE);
        addParticleSpawner(entity, victim,
                new ParticleComponent(SpriteDataUtil.getRandomPart(sc.getSprite(), PARTICLE_SIZE, PARTICLE_SIZE),
                        sc.getScale()),
                "missile_hit_particle_spawner_industry");

        Engine.getEngine().getNEntityStream().removeEntity(entity);
    }
}