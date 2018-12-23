package code.factories.components;


import code.components.Component;
import code.components.damage.DamageComponent;

/**
 * Created by theo on 8/06/17.
 */
public class DamageComponentFactory extends ComponentFactory {
    public DamageComponentFactory(){

    }

    public Component make(){
        return new DamageComponent();
    }
}
