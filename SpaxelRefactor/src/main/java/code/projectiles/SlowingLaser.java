package code.projectiles;

import code.entity.Actor;
import code.graphics.SpriteData;
import code.inventory.SpeedEffect;

/**
 * Created by theo on 12-5-2016.
 */
public class SlowingLaser extends Projectile {
    public SlowingLaser(float x, float y, float rot, SpriteData sprite, SpriteData trail, int damage, int life, float speed) {
        super(x, y, rot, sprite, trail, damage, life, speed);
    }

    public void hit(Actor a){
        super.hit(a);
        a.addStatusEffect(new SpeedEffect(100, .5f));
    }
}
