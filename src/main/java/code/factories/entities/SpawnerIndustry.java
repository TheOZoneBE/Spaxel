package code.factories.entities;

import code.components.Component;
import code.components.ComponentType;
import code.components.particle.ParticleComponent;
import code.components.position.PositionComponent;
import code.entity.EntityType;
import code.entity.Entity;

import java.util.Map;

/**
 * Created by theo on 6/06/17.
 */
public class SpawnerIndustry extends EntityIndustry {
    public Entity produce(PositionComponent pc, ParticleComponent pac) {
        Entity entity = new Entity(EntityType.SPAWNER);
        Map<ComponentType, Component> components = buildComponents();
        components.put(pac.getType(), pac);
        components.put(pc.getType(), pc);
        entity.setComponents(components);
        return entity;
    }
}
