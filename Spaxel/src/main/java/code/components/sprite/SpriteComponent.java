package code.components.sprite;

import code.components.Component;
import code.components.ComponentType;
import code.engine.Engine;
import code.graphics.SpriteData;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by theo on 3/06/17.
 */
public class SpriteComponent extends Component {
    private SpriteData sprite;
    private int scale;

    private SpriteComponent(){
        super(ComponentType.SPRITE);
    }

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

    @JsonSetter("sprite")
    public void setSprite(String spriteName) {
        this.sprite = Engine.getEngine().getSpriteAtlas().get(spriteName);
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}
