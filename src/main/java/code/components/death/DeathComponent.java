package code.components.death;

import code.components.Component;
import code.components.ComponentType;
import code.engine.NEntity;

/**
 * Created by theo on 24/06/17.
 */
public abstract class DeathComponent extends Component {
    protected DeathType deathType;

    public DeathComponent(DeathType deathType) {
        super(ComponentType.DEATH);
        this.deathType = deathType;
    }

    public abstract void die(NEntity entity);
}
