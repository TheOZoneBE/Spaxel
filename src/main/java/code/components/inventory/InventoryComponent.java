package code.components.inventory;

import code.components.Component;
import code.components.ComponentType;
import code.components.item.ItemComponent;
import code.components.link.LinkComponent;
import code.components.stack.StackComponent;
import code.engine.Engine;
import code.engine.NEntity;

import java.util.List;

/**
 * Created by theo on 24/06/17.
 */
public class InventoryComponent extends Component {
    private List<NEntity> items;

    public InventoryComponent(ComponentType type, List<NEntity> items) {
        super(type);
        this.items = items;
    }

    public List<NEntity> getItems() {
        return items;
    }

    public void setItems(List<NEntity> items) {
        this.items = items;
    }

    public void addItem(NEntity item){
        String itemName = ((ItemComponent)item.getComponent(ComponentType.ITEM)).getName();
        boolean contains = false;
        for (NEntity entity: items){
            String otherName = ((ItemComponent)entity.getComponent(ComponentType.ITEM)).getName();
            if(itemName.equals(otherName)){
                StackComponent sc = (StackComponent)entity.getComponent(ComponentType.STACK);
                sc.setStacks(sc.getStacks()+ 1);
                contains = true;
                break;
            }
        }
        if(!contains){
            items.add(item);
        }
    }

    public void addCascade(NEntity entity){
        for (NEntity e: items){
            e.addComponent(new LinkComponent(entity));
            Engine.getEngine().getNEntityStream().addEntity(e);
        }
    }

    public void removeCascade(){
        for (NEntity e: items){
            Engine.getEngine().getNEntityStream().removeEntity(e);
        }
    }
}
