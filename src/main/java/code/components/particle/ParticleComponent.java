package code.components.particle;

import code.components.Component;
import code.components.ComponentType;
import code.graphics.texture.Texture;

/**
 * Created by theo on 18/06/17.
 */
public class ParticleComponent extends Component {
    private Texture particle;
    private double scale;

    public ParticleComponent(Texture particle, double scale) {
        super(ComponentType.PARTICLE);
        this.particle = particle;
        this.scale = scale;
    }

    public Texture getParticle() {
        return particle;
    }

    public void setParticle(Texture particle) {
        this.particle = particle;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public Component copy() {
        return new ParticleComponent(particle, scale);
    }
}
