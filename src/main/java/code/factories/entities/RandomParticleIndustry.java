package code.factories.entities;

import code.components.storage.age.AgeStorage;
import code.components.storage.change.ChangeStorage;
import code.components.storage.renderable.RenderableStorage;
import code.components.storage.transformation.TransformationStorage;
import code.components.Component;
import code.components.ComponentType;
import code.entity.EntityType;
import code.entity.Entity;
import java.util.Map;

/**
 * Created by theo on 5/06/17.
 */
public class RandomParticleIndustry extends EntityIndustry {
    public Entity produce(TransformationStorage pc, AgeStorage ac, ChangeStorage vc,
            RenderableStorage sc) {
        Entity entity = new Entity(EntityType.RANDOM_PARTICLE);
        Map<ComponentType, Component> components = buildComponents();
        components.put(pc.getType(), pc);
        components.put(ac.getType(), ac);
        components.put(vc.getType(), vc);
        components.put(sc.getType(), sc);
        entity.setComponents(components);
        return entity;
    }
}
