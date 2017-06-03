package code.engine;

import code.components.Component;
import code.components.ComponentType;

import java.util.EnumMap;

/**
 * Created by theo on 31/05/17.
 */
public class NEntity {
    private EntityType type;
    private EnumMap<ComponentType, Component> components;

    public EntityType getType(){
        return type;
    }

    public EnumMap<ComponentType, Component> getComponents(){
        return components;
    }

    public Component getComponent(ComponentType type){
        return components.get(type);
    }

    public void addComponent(Component component){
        components.put(component.getType(), component);
        Engine.getEngine().getNEntityStream().addComponent(component.getType(), this);
    }

    public void removeComponent(Component component){
        components.remove(component.getType());
        Engine.getEngine().getNEntityStream().removeComponent(component.getType(), this);
    }

}
