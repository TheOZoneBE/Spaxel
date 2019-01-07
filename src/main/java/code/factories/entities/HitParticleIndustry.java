package code.factories.entities;

import code.components.age.AgeComponent;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.components.velocity.VelocityComponent;
import code.components.Component;
import code.components.ComponentType;
import code.entity.EntityType;
import code.entity.Entity;

import java.util.Map;

/**
 * Created by theo on 5/06/17.
 */
public class HitParticleIndustry extends EntityIndustry {
    public Entity produce(PositionComponent pc, AgeComponent ac, VelocityComponent vc, SpriteComponent sc) {
        Entity entity = new Entity(EntityType.HITPARTICLE);
        Map<ComponentType, Component> components = buildComponents();
        components.put(pc.getType(), pc);
        components.put(ac.getType(), ac);
        components.put(vc.getType(), vc);
        components.put(sc.getType(), sc);
        entity.setComponents(components);
        return entity;
    }
}
