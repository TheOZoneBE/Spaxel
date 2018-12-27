package code.components.death.projectile;

import code.Constants;
import code.components.ComponentType;
import code.components.death.DeathComponent;
import code.components.death.DeathType;
import code.components.link.LinkComponent;
import code.components.move.MoveComponent;
import code.components.position.PositionComponent;
import code.components.velocity.VelocityComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.factories.entities.ProjectileIndustry;
import code.math.VectorF;

/**
 * Created by theod on 4-7-2017.
 */
public class ClusterMissileDeathComponent extends DeathComponent {
    private static final int MISSILE_SPLIT = 6;

    public ClusterMissileDeathComponent() {
        super(DeathType.CLUSTER_MISSILE);
    }

    public void die(NEntity entity) {
        PositionComponent pc = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        ProjectileIndustry pri = (ProjectileIndustry) Engine.getEngine().getIndustryMap()
                .get("cluster_shrapnel_projectile_industry");
        float rot = 0;
        for (int i = 0; i < MISSILE_SPLIT; i++) {
            NEntity projectile = pri.produce(new PositionComponent(pc.getCoord(), rot),
                    (LinkComponent) entity.getComponent(ComponentType.LINK));
            MoveComponent pmc = (MoveComponent) projectile.getComponent(ComponentType.MOVE);
            float dx = (float) Math.sin(rot) * pmc.getMaxSpeed();
            float dy = (float) Math.cos(rot) * pmc.getMaxSpeed();
            projectile.addComponent(new VelocityComponent(new VectorF(dx, dy), 0));
            Engine.getEngine().getNEntityStream().addEntity(projectile);
            rot += Constants.FULL_CIRCLE / MISSILE_SPLIT;
        }
    }
}
