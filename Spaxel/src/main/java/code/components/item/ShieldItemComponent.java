package code.components.item;

import code.components.ComponentType;
import code.components.link.LinkComponent;
import code.components.render.RenderComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.inventory.ShieldItem;

/**
 * Created by theo on 8/07/17.
 */
public class ShieldItemComponent extends ItemComponent {
    protected int capacity;
    protected int maxCapacity;
    protected NEntity effect;

    public ShieldItemComponent(String name, int capacity, int maxCapacity, NEntity effect){
        super(ItemType.SHIP, name);
        this.capacity = capacity;
        this.maxCapacity = maxCapacity;
        this.effect = effect;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public NEntity getEffect() {
        return effect;
    }

    public void setEffect(NEntity effect) {
        this.effect = effect;
    }

    public void addCascade(NEntity entity){
        effect.addComponent(new LinkComponent(entity));
        ((RenderComponent)effect.getComponent(ComponentType.RENDER)).setVisible(true);
        Engine.getEngine().getNEntityStream().addEntity(effect);
    }

    public void removeCascade(){
        Engine.getEngine().getNEntityStream().removeEntity(effect);
    }
}
