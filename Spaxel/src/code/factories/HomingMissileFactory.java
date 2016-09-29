package code.factories;

import code.graphics.SpriteData;
import code.projectiles.HomingMissile;
import code.projectiles.Projectile;

/**
 * Created by theo on 13-5-2016.
 */
public class HomingMissileFactory extends ProjectileFactory {
    public HomingMissileFactory(SpriteData sprite, SpriteData trail, int damage, int life, float speed) {
        super(sprite,trail, damage, life, speed);
    }

    public Projectile make(float x, float y, float rot){
        return new HomingMissile(x, y, rot, sprite, trail, damage, life, speed);
    }
}
