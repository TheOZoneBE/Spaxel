package code.projectiles;

import code.entity.Actor;
import code.graphics.SpriteData;
import code.inventory.ShootEffect;
import code.inventory.SpeedEffect;

/**
 * Created by theo on 13-5-2016.
 */
public class HackingMissile extends Projectile {
    public HackingMissile(float x, float y, float rot, SpriteData sprite, SpriteData trail, int damage, int life, float speed) {
        super(x, y, rot, sprite,trail, damage, life, speed);
    }

    public void hit(Actor a){
        a.addStatusEffect(new ShootEffect(150));
        a.addStatusEffect(new SpeedEffect(150, 0.5f));
        super.hit(a);
    }
}
