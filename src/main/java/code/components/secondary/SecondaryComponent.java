package code.components.secondary;

import code.components.ComponentType;
import code.components.Component;
import code.components.inventory.InventoryComponent;
import code.engine.NEntity;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by theo on 19/06/17.
 */
public class SecondaryComponent extends InventoryComponent {

    public SecondaryComponent(List<NEntity> items) {
        super(ComponentType.SECONDARY, items);
    }

    public Component copy() {
        List<NEntity> copied = new ArrayList<>();
        for (NEntity item : items) {
            copied.add(item.copy());
        }

        return new SecondaryComponent(copied);
    }
}