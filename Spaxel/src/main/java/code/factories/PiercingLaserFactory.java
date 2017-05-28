package code.factories;

import code.graphics.SpriteData;
import code.projectiles.PiercingLaser;
import code.projectiles.Projectile;

/**
 * Created by theo on 11-5-2016.
 */
public class PiercingLaserFactory extends ProjectileFactory {
    public PiercingLaserFactory(SpriteData sprite, SpriteData trail, int damage, int life, float speed) {
        super(sprite,trail,  damage, life, speed);
    }

    public Projectile make(float x, float y, float rot){
        return new PiercingLaser(x, y, rot, sprite,trail, damage, life, speed);
    }
}
