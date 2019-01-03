package code.factories.components;

import code.components.Component;
import code.components.primary.PrimaryComponent;
import code.engine.Engine;
import code.engine.Resources;
import code.engine.NEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theo on 19/06/17.
 */
public class PrimaryComponentFactory extends ComponentFactory {
    private List<String> itemIndustries;

    public PrimaryComponentFactory() {
        super();
    }

    @Override
    public Component make() {
        List<NEntity> items = new ArrayList<>();
        for (String factory : itemIndustries) {
            items.add(Resources.get().getIndustryMap().get(factory).produce());
        }
        return new PrimaryComponent(items);
    }

    public List<String> getItemIndustries() {
        return itemIndustries;
    }

    public void setItemIndustries(List<String> itemIndustries) {
        this.itemIndustries = itemIndustries;
    }
}
