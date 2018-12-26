package code.components.spawner;

import code.components.ComponentType;
import code.components.particle.ParticleComponent;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.factories.entities.TrailSegmentIndustry;

import java.util.Collections;
import java.util.List;

/**
 * Created by theod on 25-9-2017.
 */
public class ShipFragmentSpawnerComponent extends SpawnerComponent {
    public ShipFragmentSpawnerComponent(int rate) {
        super(SpawnerType.SHIP_FRAGMENT, rate);
    }

    public List<NEntity> spawn(NEntity entity){
        PositionComponent pc = (PositionComponent)entity.getComponent(ComponentType.POSITION);
        ParticleComponent pac = (ParticleComponent)entity.getComponent(ComponentType.PARTICLE);
        TrailSegmentIndustry tsi = (TrailSegmentIndustry) Engine.getEngine().getIndustryMap().get("ship_fragment_industry");
        return Collections.singletonList(
                tsi.produce(
                        (PositionComponent)pc.clone(),
                        new SpriteComponent(pac.getParticle(), pac.getScale())));
    }
}
