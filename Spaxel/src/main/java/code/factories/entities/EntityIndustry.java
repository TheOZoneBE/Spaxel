package code.factories.entities;


import code.components.ComponentType;
import code.components.Component;
import code.engine.EntityType;
import code.engine.NEntity;
import code.factories.components.ComponentFactory;

import java.util.EnumMap;
import java.util.List;

/**
 * Created by theo on 3/06/17.
 */
public class EntityIndustry {
    private EntityType type;
    private List<ComponentFactory> factories;

    public EntityIndustry(){

    }

    public NEntity produce(){
        NEntity entity = new NEntity(type);
        EnumMap<ComponentType, Component> components = new EnumMap<>(ComponentType.class);
        for (ComponentFactory factory: factories){
            Component c = factory.make();
            components.put(c.getType(), c);
        }
        entity.setComponents(components);
        return entity;
    }

    public List<ComponentFactory> getFactories() {
        return factories;
    }

    public void setFactories(List<ComponentFactory> factories) {
        this.factories = factories;
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }
}
