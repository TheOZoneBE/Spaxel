package code.factories;

import code.graphics.Sprite;
import code.projectiles.DisruptLaser;
import code.projectiles.Projectile;

/**
 * Created by theo on 12-5-2016.
 */
public class DisruptLaserFactory extends ProjectileFactory{
    public DisruptLaserFactory(Sprite sprite, int damage, int life, double speed) {
        super(sprite, damage, life, speed);
    }

    public Projectile make(double x, double y, double rot){
        return new DisruptLaser(x, y, rot, sprite, damage, life, speed);
    }
}
