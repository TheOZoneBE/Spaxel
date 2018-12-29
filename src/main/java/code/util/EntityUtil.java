package code.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import code.components.ComponentType;
import code.components.inventory.InventoryComponent;
import code.components.item.ItemComponent;
import code.components.item.ItemType;
import code.components.link.LinkComponent;
import code.engine.Engine;
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

    public static void addRandomItems(NEntity entity, int number, ItemType type, ComponentType ctype) {
        InventoryComponent ic = (InventoryComponent) entity.getComponent(ctype);
        for (int i = 0; i < number; i++) {
            NEntity item = Engine.getEngine().getItems().produceRandom(prop -> prop.getType() == type);
            ic.addItem(item);
            item.addComponent(new LinkComponent(entity));
        }
    }

    public static double calculateDeltaRot(double rotChange, double turnrate) {
        double deltaRot = 0;
        if (Math.abs(rotChange) < turnrate) {
            deltaRot = rotChange;
        } else if (rotChange < 0) {
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