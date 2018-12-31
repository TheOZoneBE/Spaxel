package code.ui.logic;

import code.collision.HitShape;
import code.engine.Engine;
import code.ui.elements.Element;

public class HoverLogic implements Logic {
    public void update(Element element) {
        HitShape hitbox = Engine.getEngine().getHitShapeAtlas()
                .get(element.getStyle().getProperty("hit-shape"));


    }
}
