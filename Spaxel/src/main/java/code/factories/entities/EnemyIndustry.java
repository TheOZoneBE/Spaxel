package code.factories.entities;

import code.components.Component;
import code.components.ComponentType;
import code.components.PositionComponent;
import code.engine.EntityType;
import code.engine.NEntity;
import code.math.VectorF;

import java.util.EnumMap;


/**
 * Created by theo on 7/06/17.
 */
public class EnemyIndustry extends EntityIndustry {
    public NEntity produce(VectorF coord, float rot){
        NEntity entity = new NEntity(EntityType.ENEMY);
        EnumMap<ComponentType, Component> components = buildComponents();
        components.put(ComponentType.POSITION, new PositionComponent(coord, rot));
        entity.setComponents(components);
        return entity;
    }
}
