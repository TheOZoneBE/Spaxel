package code.factories.uielements;

import code.components.ComponentType;
import code.components.cooldown.CooldownComponent;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.components.stack.StackComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.math.VectorD;
import code.ui.UIBar;
import code.ui.UIElement;
import code.ui.UILabel;
import code.ui.UIVisual;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theod on 29-6-2017.
 */
public class ItemViewFactory {
    public UIElement produce(VectorD pos, NEntity item) {
        SpriteComponent sc = (SpriteComponent) item.getComponent(ComponentType.SPRITE);
        CooldownComponent cc = (CooldownComponent) item.getComponent(ComponentType.COOLDOWN);
        StackComponent stc = (StackComponent) item.getComponent(ComponentType.STACK);

        UIVisual base = new UIVisual();
        base.setPosition(new PositionComponent(pos, 0));
        base.setSprite(new SpriteComponent(sc.getSprite(), 2));
        UIBar cooldown = new UIBar();
        cooldown.setSprite(new SpriteComponent(Engine.getEngine().getSpriteAtlas().get("cooldown_bar"), 2));
        cooldown.setWidth(64);
        cooldown.setPercent((double) cc.getCd() / cc.getCdAmount());
        cooldown.setPosition(new PositionComponent(pos.sum(new VectorD(-32, 0)), 1));
        UILabel stacks = new UILabel();
        stacks.setPosition(new PositionComponent(pos.sum(new VectorD(20, 20)), 0));
        stacks.setScale(1);
        stacks.setText(String.valueOf(stc.getStacks()));

        List<UIElement> children = new ArrayList<>();
        children.add(cooldown);
        children.add(stacks);
        base.setChildren(children);

        return base;
    }
}
