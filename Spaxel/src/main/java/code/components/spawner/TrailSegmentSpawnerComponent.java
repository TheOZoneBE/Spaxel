package code.components.spawner;

import code.components.ComponentType;
import code.components.particle.ParticleComponent;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.factories.entities.TrailSegmentIndustry;
import code.math.VectorF;

import java.util.Collections;
import java.util.List;

/**
 * Created by theo on 18/06/17.
 */
public class TrailSegmentSpawnerComponent extends SpawnerComponent {
    public TrailSegmentSpawnerComponent(int rate) {
        super(SpawnerType.TRAILSEGMENT, rate);
    }

    public List<NEntity> spawn(NEntity entity){
        PositionComponent pc = (PositionComponent)entity.getComponent(ComponentType.POSITION);
        ParticleComponent pac = (ParticleComponent)entity.getComponent(ComponentType.PARTICLE);
        TrailSegmentIndustry tsi = (TrailSegmentIndustry) Engine.getEngine().getIndustryMap().get("trail_segment_industry");
        return Collections.singletonList(tsi.produce(pc,new SpriteComponent(pac.getParticle(), pac.getScale())));
    }
}
