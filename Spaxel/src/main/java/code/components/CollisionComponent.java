package code.components;


import code.collision.HitShape;

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
}
