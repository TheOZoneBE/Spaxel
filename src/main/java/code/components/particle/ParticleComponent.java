package code.components.particle;

import code.components.Component;
import code.components.ComponentType;
import code.graphics.SpriteData;

/**
 * Created by theo on 18/06/17.
 */
public class ParticleComponent extends Component {
    private SpriteData particle;
    private double scale;

    public ParticleComponent(SpriteData particle, double scale) {
        super(ComponentType.PARTICLE);
        this.particle = particle;
        this.scale = scale;
    }

    public SpriteData getParticle() {
        return particle;
    }

    public void setParticle(SpriteData particle) {
        this.particle = particle;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public Component copy(){
        return new ParticleComponent(particle, scale);
    }
}
