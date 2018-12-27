package code.components.death;

import code.components.Component;
import code.components.ComponentType;
import code.engine.NEntity;

/**
 * Created by theo on 24/06/17.
 */
public abstract class DeathComponent extends Component {
    public DeathComponent(DeathType deathType) {
        super(ComponentType.DEATH);
    }

    public abstract void die(NEntity entity);
}
