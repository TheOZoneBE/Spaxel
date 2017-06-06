package code.factories.entities;

import code.components.*;
import code.engine.EntityType;
import code.engine.NEntity;
import code.graphics.SpriteData;
import code.math.VectorF;

import java.util.EnumMap;

/**
 * Created by theo on 5/06/17.
 */
public class HitParticleIndustry extends EntityIndustry {
    public NEntity produce(VectorF pos, float rot, int life, int maxLife, VectorF velocity, float deltaRot, SpriteData sprite, int scale){
        NEntity entity = new NEntity(EntityType.HITPARTICLE);
        EnumMap<ComponentType, Component> components = buildComponents();
        PositionComponent pc = new PositionComponent(pos, rot);
        AgeComponent ac = new AgeComponent(life, maxLife);
        VelocityComponent vc = new VelocityComponent(velocity, deltaRot);
        SpriteComponent rc = new SpriteComponent(sprite,scale);
        components.put(pc.getType(), pc);
        components.put(ac.getType(), ac);
        components.put(vc.getType(), vc);
        components.put(rc.getType(), rc);
        entity.setComponents(components);
        return entity;
    }
}
