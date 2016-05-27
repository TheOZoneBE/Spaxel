package code.projectiles;

import code.entity.Actor;
import code.entity.Enemy;
import code.graphics.Sprite;

/**
 * Created by theo on 11-5-2016.
 */
public class PiercingLaser extends Projectile {
    public PiercingLaser(double x, double y, double rot, Sprite sprite,Sprite trail, int damage, int life, double speed) {
        super(x, y, rot, sprite,trail, damage, life, speed);
    }

    public void hit(Actor a){
        a.setHealth(a.getHealth() - damage);
    }
}
