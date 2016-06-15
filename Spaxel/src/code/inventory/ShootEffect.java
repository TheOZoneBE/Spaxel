package code.inventory;

import code.entity.Actor;
import code.entity.Enemy;

/**
 * Created by theo on 12-5-2016.
 */
public class ShootEffect extends StatusEffect{
    public ShootEffect(int life) {
        super(life);
    }

    public void affect(Actor e){
        e.setCanshoot(false);
    }

    public void undo(Actor e){
        e.setCanshoot(true);
    }
}
