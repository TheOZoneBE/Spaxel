package code.components.sprite;

import code.components.Component;
import code.components.ComponentType;
import code.graphics.SpriteData;

/**
 * Created by theo on 3/06/17.
 */
public class SpriteComponent extends Component {
    private SpriteData sprite;
    private int scale;

    public SpriteComponent(SpriteData sprite, int scale) {
        super(ComponentType.SPRITE);
        this.sprite = sprite;
        this.scale = scale;
    }

    public SpriteData getSprite() {
        return sprite;
    }

    public void setSprite(SpriteData sprite) {
        this.sprite = sprite;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}
