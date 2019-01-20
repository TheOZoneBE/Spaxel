package code.factories.components;

import code.collision.HitShape;
import code.components.storage.hitshape.HitshapeStorage;
import code.components.Component;
import code.engine.Resources;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by theo on 3/06/17.
 */
public class CollisionComponentFactory extends ComponentFactory {
    private HitShape hitShape;

    public CollisionComponentFactory() {
        super();
    }

    @Override
    public Component make() {
        return new HitshapeStorage(hitShape);
    }

    public HitShape getHitShape() {
        return hitShape;
    }

    public void setHitShape(HitShape hitShape) {
        this.hitShape = hitShape;
    }

    @JsonSetter("hitShape")
    public void setHitShape(String hitShapeName) {
        this.hitShape = Resources.get().getHitShapeAtlas().get(hitShapeName);
    }
}
