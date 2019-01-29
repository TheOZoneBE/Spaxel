package code.components.behaviour.hit;

import code.components.storage.projectile.ProjectileStorage;
import code.entity.Entity;

/**
 * Created by theod on 28-6-2017.
 */
public class PiercingLaserProjectile extends ProjectileHandler {
    // TODO private static final int PARTICLE_SIZE = 3;

    public PiercingLaserProjectile() {
        super(HitType.PIERCING_LASER);
    }

    public void payload(Entity entity, Entity victim, ProjectileStorage projStore) {
        projStore.setDamage(projStore.getDamage() / 2);
    }
}
