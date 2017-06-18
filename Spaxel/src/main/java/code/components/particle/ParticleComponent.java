package code.components.particle;

import code.components.Component;
import code.components.ComponentType;
import code.graphics.SpriteData;

/**
 * Created by theo on 18/06/17.
 */
public class ParticleComponent extends Component {
    private SpriteData particle;
    private int scale;
    public ParticleComponent(SpriteData particle, int scale) {
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

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}
