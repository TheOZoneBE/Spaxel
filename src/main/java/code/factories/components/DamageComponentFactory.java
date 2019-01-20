package code.factories.components;

import code.components.Component;
import code.components.storage.damage.DamageStorage;

/**
 * Created by theo on 8/06/17.
 */
public class DamageComponentFactory extends ComponentFactory {
    public DamageComponentFactory() {
        super();
    }

    @Override
    public Component make() {
        return new DamageStorage();
    }
}
