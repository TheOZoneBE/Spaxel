package code.projectiles;

import code.graphics.Sprite;

/**
 * Created by theo on 13-5-2016.
 */
public class HomingMissile extends Projectile {
    public HomingMissile(double x, double y, double rot, Sprite sprite, int damage, int life, double speed) {
        super(x, y, rot, sprite, damage, life, speed);
    }
}
