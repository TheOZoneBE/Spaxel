package code.components.primary;

import code.components.ComponentType;
import code.components.inventory.InventoryComponent;
import code.engine.NEntity;

import java.util.List;

/**
 * Created by theo on 19/06/17.
 */
public class PrimaryComponent extends InventoryComponent {
    public PrimaryComponent(List<NEntity> items) {
        super(ComponentType.PRIMARY, items);
    }
}