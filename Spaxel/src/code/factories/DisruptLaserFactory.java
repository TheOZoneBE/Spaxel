package code.factories;

import code.graphics.Sprite;
import code.projectiles.DisruptLaser;
import code.projectiles.Projectile;

/**
 * Created by theo on 12-5-2016.
 */
public class DisruptLaserFactory extends ProjectileFactory{
    public DisruptLaserFactory(Sprite sprite, Sprite trail, int damage, int life, float speed) {
        super(sprite,trail,  damage, life, speed);
    }

    public Projectile make(float x, float y, float rot){
        return new DisruptLaser(x, y, rot, sprite,trail, damage, life, speed);
    }
}
