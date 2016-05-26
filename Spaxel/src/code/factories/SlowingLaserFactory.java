package code.factories;

import code.graphics.Sprite;
import code.projectiles.Projectile;
import code.projectiles.SlowingLaser;

/**
 * Created by theo on 12-5-2016.
 */
public class SlowingLaserFactory extends ProjectileFactory {
    public SlowingLaserFactory(Sprite sprite,Sprite trail,  int damage, int life, double speed) {
        super(sprite, trail, damage, life, speed);
    }

    public Projectile make(double x, double y, double rot){
        return new SlowingLaser(x, y, rot, sprite,trail,  damage, life, speed);
    }
}
