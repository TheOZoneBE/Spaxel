package code.projectiles;

import code.entity.Actor;
import code.entity.Enemy;
import code.graphics.Sprite;
import code.inventory.ShootEffect;
import code.inventory.SpeedEffect;

/**
 * Created by theo on 13-5-2016.
 */
public class HackingMissile extends Projectile {
    public HackingMissile(double x, double y, double rot, Sprite sprite, Sprite trail, int damage, int life, double speed) {
        super(x, y, rot, sprite,trail, damage, life, speed);
    }

    public void hit(Actor a){
        a.addStatusEffect(new ShootEffect(150));
        a.addStatusEffect(new SpeedEffect(150, 0));
        super.hit(a);
    }
}
