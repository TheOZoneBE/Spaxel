package code.components.storage.hitshape;

import code.collision.HitShape;
import code.components.Storage;
import code.components.ComponentType;

/**
 * Represent a collidable for an entity
 * 
 * Created by theo on 3/06/17.
 */
public class HitshapeStorage extends Storage {
    private HitShape hitShape;

    public HitshapeStorage() {
        super(ComponentType.HITSHAPE);
    }

    /**
     * Create a new HitshapeStorage with the specified hitShape
     * 
     * @param hitShape the hitshape of the component
     */
    public HitshapeStorage(HitShape hitShape) {
        super(ComponentType.HITSHAPE);
        this.hitShape = hitShape;
    }

    public HitShape getHitShape() {
        return hitShape;
    }

    public void setHitShape(HitShape hitShape) {
        this.hitShape = hitShape;
    }

    public HitshapeStorage copy() {
        return new HitshapeStorage(hitShape);
    }
}
