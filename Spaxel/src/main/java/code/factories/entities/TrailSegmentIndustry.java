package code.factories.entities;

import code.components.Component;
import code.components.ComponentType;
import code.components.PositionComponent;
import code.components.SpriteComponent;
import code.engine.EntityType;
import code.engine.NEntity;
import code.graphics.SpriteData;
import code.math.VectorF;

import java.util.EnumMap;

/**
 * Created by theo on 5/06/17.
 */
public class TrailSegmentIndustry extends EntityIndustry{
    public NEntity produce(VectorF pos, float rot, SpriteData sprite, int scale){
        NEntity entity = new NEntity(EntityType.HITPARTICLE);
        EnumMap<ComponentType, Component> components = buildComponents();
        PositionComponent pc = new PositionComponent(pos, rot);
        SpriteComponent rc = new SpriteComponent(sprite,scale);
        components.put(pc.getType(), pc);
        components.put(rc.getType(), rc);
        entity.setComponents(components);
        return entity;
    }
}
