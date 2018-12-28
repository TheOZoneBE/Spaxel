package code.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import code.components.ComponentType;
import code.components.inventory.InventoryComponent;
import code.components.item.ItemComponent;
import code.engine.NEntity;

public final class EntityUtil {
    private EntityUtil() {

    }

    public static List<String> getAllItemNames(NEntity entity) {
        List<String> items = new ArrayList<>();
        ComponentType[] types = { ComponentType.PRIMARY, ComponentType.SECONDARY, ComponentType.SHIP };
        for (ComponentType type : types) {
            InventoryComponent inventory = (InventoryComponent) entity.getComponent(type);
            items.addAll(inventory.getItems().stream()
                    .map((NEntity item) -> ((ItemComponent) item.getComponent(ComponentType.ITEM)).getName())
                    .collect(Collectors.toList()));
        }
        return items;
    }
}