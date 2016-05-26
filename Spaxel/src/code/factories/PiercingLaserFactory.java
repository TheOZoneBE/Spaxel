package code.factories;

import code.graphics.Sprite;
import code.projectiles.PiercingLaser;
import code.projectiles.Projectile;

/**
 * Created by theo on 11-5-2016.
 */
public class PiercingLaserFactory extends ProjectileFactory {
    public PiercingLaserFactory(Sprite sprite, Sprite trail, int damage, int life, double speed) {
        super(sprite,trail,  damage, life, speed);
    }

    public Projectile make(double x, double y, double rot){
        return new PiercingLaser(x, y, rot, sprite,trail, damage, life, speed);
    }
}
