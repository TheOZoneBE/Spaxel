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
 * Created by theod on 25-9-2017.
 */
public class ShipFragmentSpawnerComponent extends SpawnerComponent {
    public ShipFragmentSpawnerComponent(int rate) {
        super(SpawnerType.SHIP_FRAGMENT, rate);
    }

    public List<Entity> spawn(Entity entity){
        PositionComponent pc = (PositionComponent)entity.getComponent(ComponentType.POSITION);
        ParticleComponent pac = (ParticleComponent)entity.getComponent(ComponentType.PARTICLE);
        TrailSegmentIndustry tsi = (TrailSegmentIndustry) Resources.get().getIndustryMap().get("ship_fragment_industry");
        return Collections.singletonList(
                tsi.produce(
                        (PositionComponent)pc.copy(),
                        new SpriteComponent(pac.getParticle(), pac.getScale())));
    }

    public Component copy() {
        return new ShipFragmentSpawnerComponent(rate);
    }
}
