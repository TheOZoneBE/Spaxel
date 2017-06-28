package code.factories.components;

import code.components.actor.ActorComponent;
import code.components.Component;

/**
 * Created by theod on 28-6-2017.
 */
public class ActorComponentFactory extends ComponentFactory {
    private boolean canShoot;
    private boolean canMove;

    public Component make(){
        return new ActorComponent(canShoot, canMove);
    }

    public boolean isCanShoot() {
        return canShoot;
    }

    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
