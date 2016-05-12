package code.inventory;

import code.entity.Enemy;

/**
 * Created by theo on 12-5-2016.
 */
public class SpeedEffect extends StatusEffect {
    private double speedModifier;

    public SpeedEffect(int life, double speedModifier){
        super(life);
        this.speedModifier = speedModifier;
    }

    public void affect(Enemy e){
        e.setMaxspeed(e.getMaxspeed()*speedModifier);
    }
}
