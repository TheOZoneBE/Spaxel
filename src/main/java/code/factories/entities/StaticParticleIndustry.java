package code.factories.entities;

import code.components.Component;
import code.components.ComponentType;
import code.components.storage.transformation.TransformationStorage;
import code.components.storage.renderable.RenderableStorage;
import code.entity.EntityType;
import code.entity.Entity;
import java.util.Map;

/**
 * Created by theo on 5/06/17.
 */
public class StaticParticleIndustry extends EntityIndustry {
    public Entity produce(TransformationStorage pc, RenderableStorage rc) {
        Entity entity = new Entity(EntityType.RANDOM_PARTICLE);
        Map<ComponentType, Component> components = buildComponents();
        components.put(pc.getType(), pc);
        components.put(rc.getType(), rc);
        entity.setComponents(components);
        return entity;
    }
}
