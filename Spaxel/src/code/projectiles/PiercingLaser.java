package code.projectiles;

import code.entity.Actor;
import code.entity.Enemy;
import code.graphics.Sprite;

/**
 * Created by theo on 11-5-2016.
 */
public class PiercingLaser extends Projectile {
    public PiercingLaser(float x, float y, float rot, Sprite sprite,Sprite trail, int damage, int life, float speed) {
        super(x, y, rot, sprite,trail, damage, life, speed);
    }

    public void hit(Actor a){
        a.setHealth(a.getHealth() - damage);
    }
}
