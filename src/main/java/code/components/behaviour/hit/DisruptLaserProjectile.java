package code.components.behaviour.hit;

import code.components.storage.projectile.ProjectileStorage;
import code.entity.Entity;

/**
 * Created by theod on 28-6-2017.
 */
public class DisruptLaserProjectile extends ProjectileHandler {
    // TODO private static final int PARTICLE_SIZE = 3;

    public DisruptLaserProjectile() {
        super(HitType.DISRUPT_LASER);
    }

    public void payload(Entity entity, Entity victim, ProjectileStorage projStore) {
        addEffect(victim, projStore.getOnHitEffect());

        entity.destroy();
    }
}
