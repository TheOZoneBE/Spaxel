package code.factories.elements;

import code.components.ComponentType;
import code.components.cooldown.CooldownComponent;
import code.components.sprite.SpriteComponent;
import code.components.stack.StackComponent;
import code.engine.NEntity;
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
    public static Element createItemView(NEntity item) {
        SpriteComponent sc = (SpriteComponent) item.getComponent(ComponentType.SPRITE);
        CooldownComponent cc = (CooldownComponent) item.getComponent(ComponentType.COOLDOWN);
        StackComponent stc = (StackComponent) item.getComponent(ComponentType.STACK);

        Element base = new Element();
        base.setClasses("inventory_item");
        base.setStyleProperty("sprite", sc.getType().getName());

        Element cooldown = new Element();
        cooldown.setClasses("item_cooldown");
        cooldown.setStyleProperty("completion",
                String.valueOf((double) cc.getCd() / cc.getCdAmount()));

        Element stacks = new Element();
        stacks.setClasses("item_stacks");
        stacks.setStyleProperty("text", String.valueOf(stc.getStacks()));

        base.addElement(cooldown);
        base.addElement(stacks);

        return base;
    }
}
