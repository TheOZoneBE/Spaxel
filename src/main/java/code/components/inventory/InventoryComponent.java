package code.components.inventory;

import code.components.Component;
import code.components.ComponentType;
import code.components.item.ItemComponent;
import code.components.link.LinkComponent;
import code.components.stack.StackComponent;
import code.engine.Engine;
import code.entity.Entity;

import java.util.List;

/**
 * Created by theo on 24/06/17.
 */
public abstract class InventoryComponent extends Component {
    protected List<Entity> items;

    public InventoryComponent(ComponentType type, List<Entity> items) {
        super(type);
        this.items = items;
    }

    public List<Entity> getItems() {
        return items;
    }

    public void setItems(List<Entity> items) {
        this.items = items;
    }

    public void addItem(Entity item) {
        String itemName = ((ItemComponent) item.getComponent(ComponentType.ITEM)).getName();
        boolean contains = false;
        for (Entity entity : items) {
            String otherName = ((ItemComponent) entity.getComponent(ComponentType.ITEM)).getName();
            if (itemName.equals(otherName)) {
                StackComponent sc = (StackComponent) entity.getComponent(ComponentType.STACK);
                sc.setStacks(sc.getStacks() + 1);
                contains = true;
                break;
            }
        }
        if (!contains) {
            items.add(item);
        }
    }

    @Override
    public void addCascade(Entity entity) {
        for (Entity e : items) {
            e.addComponent(new LinkComponent(entity));
            Engine.get().getNEntityStream().addEntity(e);
        }
    }

    @Override
    public void removeCascade() {
        for (Entity e : items) {
            Engine.get().getNEntityStream().removeEntity(e);
        }
    }
}
