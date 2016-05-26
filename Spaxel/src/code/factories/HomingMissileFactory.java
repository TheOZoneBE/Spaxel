package code.factories;

import code.graphics.Sprite;
import code.projectiles.HomingMissile;
import code.projectiles.Projectile;

/**
 * Created by theo on 13-5-2016.
 */
public class HomingMissileFactory extends ProjectileFactory {
    public HomingMissileFactory(Sprite sprite, Sprite trail, int damage, int life, double speed) {
        super(sprite,trail, damage, life, speed);
    }

    public Projectile make(double x, double y, double rot){
        return new HomingMissile(x, y, rot, sprite, trail, damage, life, speed);
    }
}
