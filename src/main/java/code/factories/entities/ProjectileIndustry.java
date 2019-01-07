package code.factories.entities;

import code.components.Component;
import code.components.ComponentType;
import code.components.link.LinkComponent;
import code.components.position.PositionComponent;
import code.entity.EntityType;
import code.entity.Entity;

import java.util.Map;

/**
 * Created by theo on 18/06/17.
 */
public class ProjectileIndustry extends EntityIndustry {
    public Entity produce(PositionComponent pc, LinkComponent lc){
        Entity entity = new Entity(EntityType.PROJECTILE);
        Map<ComponentType, Component> components = buildComponents();
        components.put(pc.getType(), pc);
        components.put(lc.getType(), lc);
        entity.setComponents(components);
        return entity;
    }
}
