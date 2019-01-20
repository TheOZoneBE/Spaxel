package code.components.hit;

import code.components.ComponentType;
import code.components.Component;
import code.components.storage.renderable.RenderableStorage;
import code.entity.Entity;
import code.graphics.texture.Texture;
import code.util.SpriteDataUtil;

/**
 * Created by theo on 8/07/17.
 */
public class HomingMissileHitComponent extends HitComponent {
    private static final int PARTICLE_SIZE = 4;

    public HomingMissileHitComponent(int damage) {
        super(HitType.HOMING_MISSILE, damage);
    }

    public void hit(Entity entity, Entity victim) {
        dealDamage(victim);

        RenderableStorage sc = (RenderableStorage) victim.getComponent(ComponentType.RENDERABLE);
        addParticleSpawner(
                entity, new RenderableStorage(SpriteDataUtil
                        .getRandomPart((Texture) sc.getRenderable(), PARTICLE_SIZE, PARTICLE_SIZE)),
                "missile_hit_particle_spawner_industry");

        entity.destroy();
    }

    public Component copy() {
        return new HomingMissileHitComponent(damage);
    }
}
