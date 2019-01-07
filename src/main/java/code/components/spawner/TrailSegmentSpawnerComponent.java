package code.components.spawner;

import code.components.ComponentType;
import code.components.Component;
import code.components.particle.ParticleComponent;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Resources;
import code.entity.Entity;
import code.factories.entities.TrailSegmentIndustry;

import java.util.Collections;
import java.util.List;

/**
 * Created by theo on 18/06/17.
 */
public class TrailSegmentSpawnerComponent extends SpawnerComponent {
    public TrailSegmentSpawnerComponent(int rate) {
        super(SpawnerType.TRAILSEGMENT, rate);
    }

    public List<Entity> spawn(Entity entity) {
        PositionComponent pc = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        ParticleComponent pac = (ParticleComponent) entity.getComponent(ComponentType.PARTICLE);
        TrailSegmentIndustry tsi = (TrailSegmentIndustry) Resources.get().getIndustryMap()
                .get("trail_segment_industry");
        return Collections.singletonList(
                tsi.produce((PositionComponent) pc.copy(), new SpriteComponent(pac.getParticle(), pac.getScale())));
    }

    public Component copy() {
        return new TrailSegmentSpawnerComponent(rate);
    }
}
