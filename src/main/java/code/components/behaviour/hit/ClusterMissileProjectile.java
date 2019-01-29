package code.components.behaviour.hit;

import code.components.storage.projectile.ProjectileStorage;
import code.entity.Entity;

/**
 * Created by theod on 4-7-2017.
 */
public class ClusterMissileProjectile extends ProjectileHandler {
    // TODOprivate static final int PARTICLE_SIZE = 4;

    public ClusterMissileProjectile() {
        super(HitType.CLUSTER_MISSILE);
    }

    public void payload(Entity entity, Entity victim, ProjectileStorage projStore) {
        entity.destroy();
    }


}
