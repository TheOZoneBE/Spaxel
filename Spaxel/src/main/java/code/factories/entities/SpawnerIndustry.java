package code.factories.entities;

import code.components.Component;
import code.components.ComponentType;
import code.components.particle.ParticleComponent;
import code.components.position.PositionComponent;
import code.engine.EntityType;
import code.engine.NEntity;

import java.util.EnumMap;

/**
 * Created by theo on 6/06/17.
 */
public class SpawnerIndustry extends EntityIndustry {
    public NEntity produce(PositionComponent pc, ParticleComponent pac){
        NEntity entity = new NEntity(EntityType.SPAWNER);
        EnumMap<ComponentType, Component> components = buildComponents();
        components.put(pac.getType(), pac);
        components.put(pc.getType(), pc);
        entity.setComponents(components);
        return entity;
    }
}
