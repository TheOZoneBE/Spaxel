package code.components.collision;

import code.collision.HitShape;
import code.components.Component;
import code.components.ComponentType;

/**
 * Created by theo on 3/06/17.
 */
public class CollisionComponent extends Component {
    private HitShape hitShape;

    public CollisionComponent(HitShape hitShape) {
        super(ComponentType.COLLISION);
        this.hitShape = hitShape;
    }

    public HitShape getHitShape() {
        return hitShape;
    }

    public void setHitShape(HitShape hitShape) {
        this.hitShape = hitShape;
    }

    public Component copy() {
        return new CollisionComponent(hitShape);
    }
}
