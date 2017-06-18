package code.components.item;

import code.components.Component;
import code.components.ComponentType;
import code.engine.NEntity;

/**
 * Created by theo on 16/06/17.
 */
public class ItemComponent extends Component {
    private ItemType itemType;
    private String name;

    public ItemComponent(ItemType itemType, String name) {
        super(ComponentType.ITEM);
        this.itemType = itemType;
        this.name = name;
    }

    public void activate(NEntity entity){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
