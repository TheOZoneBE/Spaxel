package code.components.actor;

import code.components.Component;
import code.components.ComponentType;

/**
 * Created by theod on 28-6-2017.
 */
public class ActorComponent extends Component {
    private boolean canShoot;
    private boolean canMove;

    public ActorComponent(boolean canShoot, boolean canMove) {
        super(ComponentType.ACTOR);
        this.canShoot = canShoot;
        this.canMove = canMove;
    }

    public boolean canShoot() {
        return canShoot;
    }

    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
