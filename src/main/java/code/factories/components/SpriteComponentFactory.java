package code.factories.components;

import code.components.Component;
import code.components.sprite.SpriteComponent;
import code.engine.Resources;
import code.graphics.texture.Texture;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by theo on 3/06/17.
 */
public class SpriteComponentFactory extends ComponentFactory {
    private Texture sprite;
    private double scale;

    public SpriteComponentFactory() {
        super();
    }

    @Override
    public Component make() {
        return new SpriteComponent(sprite, scale);
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
}
