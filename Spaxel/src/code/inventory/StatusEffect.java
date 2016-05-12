package code.inventory;

import code.entity.Enemy;
import code.entity.Entity;

/**
 * Created by theo on 12-5-2016.
 */
public class StatusEffect{
    protected int life;
    private boolean alive = true;

    public StatusEffect(int life){
        this.life= life;
    }

    public void affect(Enemy e){

    }

    public void update(){
        if (life > 0){
            life--;
        }
        else if (life == 0){
            setDead();
        }
    }

    public void setDead(){
        alive = false;
    }

    public boolean isAlive(){
        return alive;
    }

}
