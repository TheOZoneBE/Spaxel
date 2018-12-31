package code.factories.uielements;

import code.components.ComponentType;
import code.components.cooldown.CooldownComponent;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.components.stack.StackComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.math.VectorD;
import code.ui.elements.UIBar;
import code.ui.elements.UIElement;
import code.ui.elements.UILabel;
import code.ui.elements.UIVisual;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theod on 29-6-2017.
 */
public class ItemViewFactory {
    private static final int ITEM_SPRITE_SCALE = 2;
    private static final int COOLDOWN_WIDTH = 64;
    private static final int HALF_COOLDOWN_WIDTH = COOLDOWN_WIDTH / 2;
    private static final int STACKS_OFFSET = 20;

    public UIElement produce(VectorD pos, NEntity item) {
        SpriteComponent sc = (SpriteComponent) item.getComponent(ComponentType.SPRITE);
        CooldownComponent cc = (CooldownComponent) item.getComponent(ComponentType.COOLDOWN);
        StackComponent stc = (StackComponent) item.getComponent(ComponentType.STACK);

        UIVisual base = new UIVisual();
        base.setPosition(new PositionComponent(pos, 0));
        base.setSprite(new SpriteComponent(sc.getSprite(), ITEM_SPRITE_SCALE));
        UIBar cooldown = new UIBar();
        cooldown.setSprite(
                new SpriteComponent(Engine.getEngine().getSpriteAtlas().get("cooldown_bar"), ITEM_SPRITE_SCALE));
        cooldown.setWidth(COOLDOWN_WIDTH);
        cooldown.setPercent((double) cc.getCd() / cc.getCdAmount());
        cooldown.setPosition(new PositionComponent(pos.sum(new VectorD(-HALF_COOLDOWN_WIDTH, 0)), 1));
        UILabel stacks = new UILabel();
        stacks.setPosition(new PositionComponent(pos.sum(new VectorD(STACKS_OFFSET, STACKS_OFFSET)), 0));
        stacks.setScale(1);
        stacks.setText(String.valueOf(stc.getStacks()));

        List<UIElement> children = new ArrayList<>();
        children.add(cooldown);
        children.add(stacks);
        base.setChildren(children);

        return base;
    }
}
