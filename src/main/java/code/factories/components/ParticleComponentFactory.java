package code.factories.components;

import code.components.Component;
import code.components.particle.ParticleComponent;
import code.engine.Engine;
import code.graphics.SpriteData;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by theo on 18/06/17.
 */
public class ParticleComponentFactory extends ComponentFactory {
    private SpriteData particle;
    private float scale;

    public ParticleComponentFactory() {
        super();
    }

    public Component make() {
        return new ParticleComponent(particle, scale);
    }

    public SpriteData getParticle() {
        return particle;
    }

    public void setParticle(SpriteData particle) {
        this.particle = particle;
    }

    @JsonSetter("particle")
    public void setSprite(String spriteName) {
        this.particle = Engine.getEngine().getSpriteAtlas().get(spriteName);
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
