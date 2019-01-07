package code.components.item;

import code.components.link.LinkComponent;
import code.engine.Engine;
import code.entity.Entity;

/**
 * Created by theo on 8/07/17.
 */
public abstract class ShieldItemComponent extends ItemComponent {
    protected int capacity;
    protected int maxCapacity;
    protected Entity effect;

    public ShieldItemComponent(String name, int capacity, int maxCapacity, Entity effect) {
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

    public Entity getEffect() {
        return effect;
    }

    public void setEffect(Entity effect) {
        this.effect = effect;
    }

    @Override
    public void addCascade(Entity entity) {
        effect.addComponent(new LinkComponent(entity));
        Engine.get().getNEntityStream().addEntity(effect);
    }

    @Override
    public void removeCascade() {
        Engine.get().getNEntityStream().removeEntity(effect);
    }
}
