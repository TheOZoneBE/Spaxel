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
}
