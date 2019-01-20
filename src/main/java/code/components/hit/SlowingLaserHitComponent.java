package code.components.hit;

import code.components.ComponentType;
import code.components.Component;
import code.components.storage.renderable.RenderableStorage;
import code.entity.Entity;
import code.graphics.texture.Texture;
import code.util.SpriteDataUtil;

/**
 * Created by theod on 28-6-2017.
 */
public class SlowingLaserHitComponent extends HitComponent {
    private static final int PARTICLE_SIZE = 3;

    public SlowingLaserHitComponent(int damage) {
        super(HitType.SLOWING_LASER, damage);
    }

    public void hit(Entity entity, Entity victim) {
        dealDamage(victim);

        RenderableStorage sc = (RenderableStorage) victim.getComponent(ComponentType.RENDERABLE);
        addParticleSpawner(
                entity, new RenderableStorage(SpriteDataUtil
                        .getRandomPart((Texture) sc.getRenderable(), PARTICLE_SIZE, PARTICLE_SIZE)),
                "laser_hit_particle_spawner_industry");

        addEffect(victim, "slow_effect_industry");

        entity.destroy();
    }

    public Component copy() {
        return new SlowingLaserHitComponent(damage);
    }
}
