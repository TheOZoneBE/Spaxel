package code.factories.components;

import code.components.Component;
import code.components.particle.ParticleComponent;
import code.engine.Resources;
import code.graphics.texture.Texture;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by theo on 18/06/17.
 */
public class ParticleComponentFactory extends ComponentFactory {
    private Texture particle;
    private double scale;

    public ParticleComponentFactory() {
        super();
    }

    @Override
    public Component make() {
        return new ParticleComponent(particle, scale);
    }

    public Texture getParticle() {
        return particle;
    }

    public void setParticle(Texture particle) {
        this.particle = particle;
    }

    @JsonSetter("particle")
    public void setSprite(String spriteName) {
        this.particle = (Texture) Resources.get().getRenderables().get(spriteName);
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
}
