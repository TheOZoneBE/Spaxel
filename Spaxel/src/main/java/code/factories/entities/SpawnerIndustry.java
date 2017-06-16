package code.factories.entities;

import code.components.Component;
import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.engine.EntityType;
import code.engine.NEntity;

import java.util.EnumMap;

/**
 * Created by theo on 6/06/17.
 */
public class SpawnerIndustry extends EntityIndustry {
    public NEntity produce(PositionComponent positionComponent, SpriteComponent spriteComponent){
        NEntity entity = new NEntity(EntityType.SPAWNER);
        EnumMap<ComponentType, Component> components = buildComponents();
        components.put(ComponentType.SPRITE, spriteComponent);
        components.put(ComponentType.POSITION, positionComponent);
        entity.setComponents(components);
        return entity;
    }
}
