package code.components;

import code.engine.NEntity;

/**
 * Created by theo on 31/05/17.
 */
public class Component {
    private ComponentType type;

    public Component(ComponentType type){
        this.type = type;
    }

    public void update(NEntity entity){

    }

    public Component clone(){
        return new Component(type);
    }

    public ComponentType getType(){
        return type;
    }
}
