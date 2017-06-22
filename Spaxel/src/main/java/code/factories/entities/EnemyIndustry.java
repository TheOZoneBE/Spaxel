package code.factories.entities;

import code.components.Component;
import code.components.ComponentType;
import code.components.link.LinkComponent;
import code.components.position.PositionComponent;
import code.components.primary.PrimaryComponent;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.math.VectorF;

import java.util.EnumMap;


/**
 * Created by theo on 7/06/17.
 */
public class EnemyIndustry extends EntityIndustry {
    public NEntity produce(PositionComponent pc){
        NEntity entity = new NEntity(EntityType.ENEMY);
        EnumMap<ComponentType, Component> components = buildComponents();
        components.put(pc.getType(), pc);
        entity.setComponents(components);
        //TODO secondary, ship*/
        return entity;
    }
}
