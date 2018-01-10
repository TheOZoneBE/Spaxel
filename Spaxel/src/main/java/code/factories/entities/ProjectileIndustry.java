package code.factories.entities;

import code.components.Component;
import code.components.ComponentType;
import code.components.link.LinkComponent;
import code.components.position.PositionComponent;
import code.engine.EntityType;
import code.engine.NEntity;

import java.util.EnumMap;

/**
 * Created by theo on 18/06/17.
 */
public class ProjectileIndustry extends EntityIndustry {
    public NEntity produce(PositionComponent pc, LinkComponent lc){
        NEntity entity = new NEntity(EntityType.PROJECTILE);
        EnumMap<ComponentType, Component> components = buildComponents();
        components.put(pc.getType(), pc);
        components.put(lc.getType(), lc);
        entity.setComponents(components);
        return entity;
    }
}
