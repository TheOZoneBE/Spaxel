package code.factories.components;

import code.collision.HitShape;
import code.components.collision.CollisionComponent;
import code.components.Component;
import code.engine.Engine;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by theo on 3/06/17.
 */
public class CollisionComponentFactory extends ComponentFactory {
    private HitShape hitShape;

    public CollisionComponentFactory() {
        super();
    }

    public Component make() {
        return new CollisionComponent(hitShape);
    }

    public HitShape getHitShape() {
        return hitShape;
    }

    public void setHitShape(HitShape hitShape) {
        this.hitShape = hitShape;
    }

    @JsonSetter("hitShape")
    public void setHitShape(String hitShapeName) {
        this.hitShape = Engine.getEngine().getHitShapeAtlas().get(hitShapeName);
    }
}
