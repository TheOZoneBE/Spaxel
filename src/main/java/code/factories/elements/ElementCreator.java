package code.factories.elements;

import code.components.ComponentType;
import code.components.storage.cooldown.CooldownStorage;
import code.components.sprite.SpriteComponent;
import code.components.stack.StackComponent;
import code.entity.Entity;
import code.ui.elements.Element;


/**
 * Creates new UI elements
 * 
 * Created by theod on 29-6-2017.
 */
public final class ElementCreator {
    private ElementCreator() {

    }

    /**
     * Create an Item View Element
     * 
     * @param item the item to create a view for
     * 
     * @return the created Element
     */
    public static Element createItemView(Entity item) {
        SpriteComponent sc = (SpriteComponent) item.getComponent(ComponentType.SPRITE);
        CooldownStorage cc = (CooldownStorage) item.getComponent(ComponentType.COOLDOWN);
        StackComponent stc = (StackComponent) item.getComponent(ComponentType.STACK);

        Element base = new Element();
        base.setClasses("inventory_item");
        base.getStyle().setProperty("sprite", sc.getSprite().getName());

        Element cooldown = new Element();
        cooldown.setClasses("item_cooldown");
        cooldown.getStyle().setProperty("completion",
                String.valueOf((double) cc.getCurrentCooldown() / cc.getMaxCooldown()));

        Element stacks = new Element();
        stacks.setClasses("item_stacks");
        stacks.getStyle().setProperty("text", String.valueOf(stc.getStacks()));

        base.addElement(cooldown);
        base.addElement(stacks);

        return base;
    }
}
