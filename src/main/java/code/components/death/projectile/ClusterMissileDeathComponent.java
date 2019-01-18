package code.components.death.projectile;

import code.Constants;
import code.components.Component;
import code.components.ComponentType;
import code.components.death.DeathComponent;
import code.components.death.DeathType;
import code.components.move.MoveComponent;
import code.components.position.PositionComponent;
import code.components.velocity.VelocityComponent;
import code.engine.Engine;
import code.entity.Entity;
import code.factories.entities.ProjectileIndustry;
import code.math.VectorD;
import code.engine.Resources;

/**
 * Created by theod on 4-7-2017.
 */
public class ClusterMissileDeathComponent extends DeathComponent {
    private static final int MISSILE_SPLIT = 6;

    public ClusterMissileDeathComponent() {
        super(DeathType.CLUSTER_MISSILE);
    }

    public void die(Entity entity) {
        PositionComponent pc = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        ProjectileIndustry pri = (ProjectileIndustry) Resources.get().getIndustryMap()
                .get("cluster_shrapnel_projectile_industry");
        double rot = 0;
        for (int i = 0; i < MISSILE_SPLIT; i++) {
            Entity projectile =
                    pri.produce(new PositionComponent(pc.getCoord(), rot), entity.getParent());
            MoveComponent pmc = (MoveComponent) projectile.getComponent(ComponentType.MOVE);
            double dx = Math.sin(rot) * pmc.getMaxSpeed();
            double dy = Math.cos(rot) * pmc.getMaxSpeed();
            projectile.addComponent(new VelocityComponent(new VectorD(dx, dy), 0));
            Engine.get().getNEntityStream().addEntity(projectile);
            rot += Constants.FULL_CIRCLE / MISSILE_SPLIT;
        }
    }

    public Component copy() {
        return new ClusterMissileDeathComponent();
    }
}
