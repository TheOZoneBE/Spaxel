package code.factories;

import code.graphics.Sprite;
import code.projectiles.Projectile;
import code.projectiles.SlowingLaser;

/**
 * Created by theo on 12-5-2016.
 */
public class SlowingLaserFactory extends ProjectileFactory {
    public SlowingLaserFactory(Sprite sprite,Sprite trail,  int damage, int life, float speed) {
        super(sprite, trail, damage, life, speed);
    }

    public Projectile make(float x, float y, float rot){
        return new SlowingLaser(x, y, rot, sprite,trail,  damage, life, speed);
    }
}
