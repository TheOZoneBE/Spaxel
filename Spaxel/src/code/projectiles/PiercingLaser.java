package code.projectiles;

import code.entity.Enemy;
import code.graphics.Sprite;

/**
 * Created by theo on 11-5-2016.
 */
public class PiercingLaser extends Projectile {
    public PiercingLaser(double x, double y, double rot, Sprite sprite, int damage, int life, double speed) {
        super(x, y, rot, sprite, damage, life, speed);
    }

    public void hit(Enemy e){
        e.setHealth(e.getHealth() - damage);
    }
}
