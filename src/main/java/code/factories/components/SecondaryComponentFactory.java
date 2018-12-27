package code.factories.components;

import code.components.Component;
import code.components.secondary.SecondaryComponent;
import code.engine.Engine;
import code.engine.NEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theo on 19/06/17.
 */
public class SecondaryComponentFactory extends ComponentFactory {
    private List<String> itemIndustries;

    public SecondaryComponentFactory() {
        super();
    }

    public Component make() {
        List<NEntity> items = new ArrayList<>();
        for (String factory : itemIndustries) {
            items.add(Engine.getEngine().getIndustryMap().get(factory).produce());
        }
        return new SecondaryComponent(items);
    }

    public List<String> getItemIndustries() {
        return itemIndustries;
    }

    public void setItemIndustries(List<String> itemIndustries) {
        this.itemIndustries = itemIndustries;
    }
}
