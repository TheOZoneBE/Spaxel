package code.factories.components;

import code.components.Component;
import code.components.sprite.SpriteComponent;
import code.engine.Resources;
import code.graphics.SpriteData;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by theo on 3/06/17.
 */
public class SpriteComponentFactory extends ComponentFactory {
    private SpriteData sprite;
    private double scale;

    public SpriteComponentFactory() {
        super();
    }

    @Override
    public Component make() {
        return new SpriteComponent(sprite, scale);
    }

    public SpriteData getSprite() {
        return sprite;
    }

    public void setSprite(SpriteData sprite) {
        this.sprite = sprite;
    }

    @JsonSetter("sprite")
    public void setSprite(String spriteName) {
        this.sprite = Resources.get().getSpriteAtlas().get(spriteName);
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
}
