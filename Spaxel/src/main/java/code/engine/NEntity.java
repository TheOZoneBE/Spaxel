package code.engine;

import code.components.Component;
import code.components.ComponentType;
import code.entity.Entity;

import java.util.EnumMap;

/**
 * Created by theo on 31/05/17.
 */
public class NEntity {
    private EntityType type;
    private EnumMap<ComponentType, Component> components;

    public NEntity(EntityType type){
        this.type = type;
        this.components = new EnumMap<>(ComponentType.class);
    }

    public EntityType getType(){
        return type;
    }

    public EnumMap<ComponentType, Component> getComponents(){
        return components;
    }

    public void setComponents(EnumMap<ComponentType, Component> components){
        this.components = components;
    }

    public Component getComponent(ComponentType type){
        return components.get(type);
    }

    public void addComponent(Component component){
        component.addCascade(this);
        components.put(component.getType(), component);
        Engine.getEngine().getNEntityStream().addComponent(component.getType(), this);
    }

    public void removeComponent(ComponentType type){
        components.get(type).removeCascade();
        components.remove(type);
        Engine.getEngine().getNEntityStream().removeComponent(type, this);
    }

    public void addCascade(){
        for (Component c: components.values()){
            c.addCascade(this);
        }
    }

    public void removeCascade(){
        for(Component c: components.values()){
            c.removeCascade();
        }
    }

}
