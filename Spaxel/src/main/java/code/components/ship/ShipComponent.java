package code.components.ship;

import code.components.Component;
import code.components.ComponentType;
import code.components.inventory.InventoryComponent;
import code.engine.NEntity;

import java.util.List;

/**
 * Created by theo on 19/06/17.
 */
public class ShipComponent extends InventoryComponent {

    public ShipComponent(List<NEntity> items) {
        super(ComponentType.SHIP, items);
    }
}
