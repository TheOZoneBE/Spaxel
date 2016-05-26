package code.factories;

import code.graphics.Sprite;
import code.projectiles.BasicMissile;
import code.projectiles.Projectile;

/**
 * Created by theo on 9-5-2016.
 */
public class BasicMissileFactory extends ProjectileFactory {
    public BasicMissileFactory(Sprite sprite,Sprite trail,  int damage, int life, double speed) {
        super(sprite,trail,  damage, life, speed);
    }

    public Projectile make(double x, double y, double rot){
        return new BasicMissile(x, y, rot, sprite,trail, damage, life, speed);
    }
}
