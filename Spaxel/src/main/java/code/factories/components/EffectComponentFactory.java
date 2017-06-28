package code.factories.components;

import code.components.Component;
import code.components.effect.EffectComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theod on 28-6-2017.
 */
public class EffectComponentFactory extends ComponentFactory {
    public Component make(){
        return new EffectComponent(new ArrayList<>());
    }
}
