package code.components.sprite;

import code.components.Component;
import code.components.ComponentType;
import code.engine.Resources;
import code.graphics.texture.Texture;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by theo on 3/06/17.
 */
public class SpriteComponent extends Component {
    private Texture sprite;
    private double scale;

    private SpriteComponent() {
        super(ComponentType.SPRITE);
    }

    public SpriteComponent(Texture sprite, double scale) {
        super(ComponentType.SPRITE);
        this.sprite = sprite;
        this.scale = scale;
    }

    public Texture getSprite() {
        return sprite;
    }

    public void setSprite(Texture sprite) {
        this.sprite = sprite;
    }

    @JsonSetter("sprite")
    public void setSprite(String spriteName) {
        this.sprite = (Texture) Resources.get().getRenderables().get(spriteName);
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public Component copy() {
        return new SpriteComponent(sprite, scale);
    }
}
