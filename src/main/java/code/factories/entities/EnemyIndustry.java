package code.factories.entities;

import code.components.Component;
import code.components.ComponentType;
import code.components.storage.transformation.TransformationStorage;
import code.entity.EntityType;
import code.entity.Entity;
import java.util.Map;

/**
 * Created by theo on 7/06/17.
 */
public class EnemyIndustry extends EntityIndustry {
    public Entity produce(TransformationStorage pc) {
        Entity entity = new Entity(EntityType.ENEMY);
        Map<ComponentType, Component> components = buildComponents();
        components.put(pc.getType(), pc);
        entity.setComponents(components);
        return entity;
    }
}
