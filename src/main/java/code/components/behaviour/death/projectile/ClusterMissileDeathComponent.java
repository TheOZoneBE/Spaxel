package code.components.behaviour.death.projectile;

import code.Constants;
import code.components.ComponentType;
import code.components.behaviour.death.DeathHandler;
import code.components.behaviour.death.DeathType;
import code.components.storage.move.MoveStorage;
import code.components.storage.transformation.TransformationStorage;
import code.components.storage.change.ChangeStorage;
import code.engine.Engine;
import code.entity.Entity;
import code.factories.entities.ProjectileIndustry;
import code.math.VectorD;
import code.engine.Resources;

/**
 * Created by theod on 4-7-2017.
 */
public class ClusterMissileDeathComponent extends DeathHandler {
    private static final int MISSILE_SPLIT = 6;

    public ClusterMissileDeathComponent() {
        super(DeathType.CLUSTER_MISSILE);
    }

    public void die(Entity entity) {
        TransformationStorage pc =
                (TransformationStorage) entity.getComponent(ComponentType.TRANSFORMATION);
        ProjectileIndustry pri = (ProjectileIndustry) Resources.get().getIndustryMap()
                .get("cluster_shrapnel_projectile_industry");
        double rot = 0;
        for (int i = 0; i < MISSILE_SPLIT; i++) {
            Entity projectile = pri.produce(pc.copy(), entity.getParent());
            MoveStorage pmc = (MoveStorage) projectile.getComponent(ComponentType.MOVE);
            double dx = Math.sin(rot) * pmc.getSpeed();
            double dy = Math.cos(rot) * pmc.getSpeed();
            projectile.addComponent(new ChangeStorage(new VectorD(dx, dy), 0, 0));
            Engine.get().getNEntityStream().addEntity(projectile);
            rot += Constants.FULL_CIRCLE / MISSILE_SPLIT;
        }
    }
}
