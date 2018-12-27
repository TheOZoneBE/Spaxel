package code.components.item;

import code.components.link.LinkComponent;
import code.engine.Engine;
import code.engine.NEntity;

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

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public NEntity getEffect() {
        return effect;
    }

    public void setEffect(NEntity effect) {
        this.effect = effect;
    }

    public void addCascade(NEntity entity){
        effect.addComponent(new LinkComponent(entity));
        Engine.getEngine().getNEntityStream().addEntity(effect);
    }

    public void removeCascade(){
        Engine.getEngine().getNEntityStream().removeEntity(effect);
    }
}