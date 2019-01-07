package code.components.ship;

import code.components.ComponentType;
import code.components.Component;
import code.components.inventory.InventoryComponent;
import code.entity.Entity;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by theo on 19/06/17.
 */
public class ShipComponent extends InventoryComponent {

    public ShipComponent(List<Entity> items) {
        super(ComponentType.SHIP, items);
    }

    public Component copy() {
        List<Entity> copied = new ArrayList<>();
        for (Entity item : items) {
            copied.add(item.copy());
        }

        return new ShipComponent(copied);
    }
}
