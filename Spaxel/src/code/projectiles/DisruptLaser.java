package code.projectiles;

import code.entity.Enemy;
import code.graphics.Sprite;
import code.inventory.ShootEffect;

/**
 * Created by theo on 12-5-2016.
 */
public class DisruptLaser extends Projectile {
    public DisruptLaser(double x, double y, double rot, Sprite sprite, int damage, int life, double speed) {
        super(x, y, rot, sprite, damage, life, speed);
    }

    public void hit(Enemy e){
        super.hit(e);
        e.addStatusEffect(new ShootEffect(100));
    }
}
