package code.components.primary;

import code.components.Component;
import code.components.ComponentType;
import code.components.inventory.InventoryComponent;
import code.components.item.ItemComponent;
import code.components.link.LinkComponent;
import code.components.stack.StackComponent;
import code.engine.Engine;
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
