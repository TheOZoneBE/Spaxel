package code.inventory;

import code.entity.Enemy;

/**
 * Created by theo on 12-5-2016.
 */
public class ShootEffect extends StatusEffect{
    public ShootEffect(int life) {
        super(life);
    }

    public void affect(Enemy e){
        e.setCanshoot(!e.getCanShoot());
    }
}
