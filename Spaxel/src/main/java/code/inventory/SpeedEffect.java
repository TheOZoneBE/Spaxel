package code.inventory;

import code.entity.Actor;
import code.entity.Enemy;

/**
 * Created by theo on 12-5-2016.
 */
public class SpeedEffect extends StatusEffect {
    private float speedModifier;

    public SpeedEffect(int life, float speedModifier){
        super(life);
        this.speedModifier = speedModifier;
    }

    public void affect(Actor e){
        e.setMaxspeed(e.getMaxspeed()*speedModifier);
    }

    public void undo(Actor e){
        e.setMaxspeed(e.getMaxspeed()/speedModifier);
    }
}
