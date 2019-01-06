package code.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import code.components.ComponentType;
import code.components.inventory.InventoryComponent;
import code.components.item.ItemComponent;
import code.components.item.ItemType;
import code.components.link.LinkComponent;
import code.engine.Resources;
import code.engine.NEntity;

/**
 * Provides utility functions for entities
 */
public final class EntityUtil {
    private EntityUtil() {

    }

    /**
     * Return a list with all the item names an entity has in its inventories
     * 
     * @param entity the entity to get the item names from
     * 
     * @return a list with item names
     */
    public static List<String> getAllItemNames(NEntity entity) {
        List<String> items = new ArrayList<>();
        // all inventory component types
        ComponentType[] types =
                {ComponentType.PRIMARY, ComponentType.SECONDARY, ComponentType.SHIP};
        for (ComponentType type : types) {
            InventoryComponent inventory = (InventoryComponent) entity.getComponent(type);
            // collect all item names of this inventory
            items.addAll(inventory.getItems().stream()
                    .map((NEntity item) -> ((ItemComponent) item.getComponent(ComponentType.ITEM))
                            .getName())
                    .collect(Collectors.toList()));
        }
        return items;
    }

    /**
     * Add a number of random items to an entity
     * 
     * @param entity the entity to add the items to
     * @param number the amount of items to add
     * @param type   the type of items to add
     * @param ctype  the type of the component to add the items to
     */
    public static void addRandomItems(NEntity entity, int number, ItemType type,
            ComponentType ctype) {
        InventoryComponent ic = (InventoryComponent) entity.getComponent(ctype);
        for (int i = 0; i < number; i++) {
            // produce a random item of the given type
            NEntity item = Resources.get().getItems().produceRandom(prop -> prop.getType() == type);
            ic.addItem(item);
            item.addComponent(new LinkComponent(entity));
        }
    }

    /**
     * Calculate the rotation for one update step based on the required rotation change and the
     * turnrate
     * 
     * @param rotChange the required rotation change
     * @param turnrate  the maximum amount of rotation that can occur
     * 
     * @return the rotation for on update step
     */
    public static double calculateDeltaRot(double rotChange, double turnrate) {
        double deltaRot = 0;
        if (Math.abs(rotChange) < turnrate) {
            // if the rotation change is less than the turnrate we can just do it
            deltaRot = rotChange;
        } else if (rotChange < 0) {
            // else decide which direction to turn
            if (rotChange < -Math.PI) {
                deltaRot = turnrate;
            } else {
                deltaRot = -turnrate;
            }
        } else {
            if (rotChange > Math.PI) {
                deltaRot = -turnrate;
            } else {
                deltaRot = turnrate;
            }
        }
        return deltaRot;
    }
}
