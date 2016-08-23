package code.factories;

import code.graphics.Sprite;
import code.projectiles.HackingMissile;
import code.projectiles.Projectile;

/**
 * Created by theo on 13-5-2016.
 */
public class HackingMissileFactory extends ProjectileFactory {
    public HackingMissileFactory(Sprite sprite,Sprite trail,  int damage, int life, float speed) {
        super(sprite, trail, damage, life, speed);
    }

    public Projectile make(float x, float y, float rot){
        return new HackingMissile(x, y, rot, sprite, trail, damage, life, speed);
    }
}
