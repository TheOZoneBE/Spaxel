package code.factories.components;

import code.components.Component;
import code.components.ship.ShipComponent;
import code.engine.Resources;
import code.engine.NEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theo on 19/06/17.
 */
public class ShipComponentFactory extends ComponentFactory {
    private List<String> itemIndustries;

    public ShipComponentFactory() {
        super();
    }

    @Override
    public Component make() {
        List<NEntity> items = new ArrayList<>();
        for (String factory : itemIndustries) {
            items.add(Resources.get().getIndustryMap().get(factory).produce());
        }
        return new ShipComponent(items);
    }

    public List<String> getItemIndustries() {
        return itemIndustries;
    }

    public void setItemIndustries(List<String> itemIndustries) {
        this.itemIndustries = itemIndustries;
    }
}
