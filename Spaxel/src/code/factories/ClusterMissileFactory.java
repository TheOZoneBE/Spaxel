package code.factories;

import code.graphics.Sprite;
import code.projectiles.BasicMissile;
import code.projectiles.ClusterMissile;
import code.projectiles.Projectile;

/**
 * Created by theo on 9-5-2016.
 */
public class ClusterMissileFactory extends ProjectileFactory {
    public ClusterMissileFactory(Sprite sprite, int damage, int life, double speed) {
        super(sprite, damage, life, speed);
    }

    public Projectile make(double x, double y, double rot){
        return new ClusterMissile(x, y, rot, sprite, damage,life, speed);
    }
}
