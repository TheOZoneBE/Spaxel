package code.factories.components;

import code.components.Component;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.graphics.SpriteData;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by theo on 3/06/17.
 */
public class SpriteComponentFactory extends ComponentFactory {
    private SpriteData sprite;
    private float scale;

    public SpriteComponentFactory(){

    }

    public Component make(){
        return new SpriteComponent(sprite, scale);
    }

    public SpriteData getSprite() {
        return sprite;
    }

    public void setSprite(SpriteData sprite) {
        this.sprite = sprite;
    }

    @JsonSetter("sprite")
    public void setSprite(String spriteName){
        this.sprite = Engine.getEngine().getSpriteAtlas().get(spriteName);
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
