package code.factories.components;

import code.components.Component;
import code.components.effect.EffectComponent;

/**
 * Created by theod on 28-6-2017.
 */
public class EffectComponentFactory extends ComponentFactory {
    public Component make(){
        return new EffectComponent();
    }
}
