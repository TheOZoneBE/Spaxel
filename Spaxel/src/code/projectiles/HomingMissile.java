package code.projectiles;

import code.engine.Engine;
import code.engine.EntityType;
import code.entity.Entity;
import code.graphics.Sprite;

import java.util.List;

/**
 * Created by theo on 13-5-2016.
 */
public class HomingMissile extends Projectile {
    public HomingMissile(double x, double y, double rot, Sprite sprite,Sprite trail,  int damage, int life, double speed) {
        super(x, y, rot, sprite,trail, damage, life, speed);
    }

    public void update(){
        List<Entity> enemies = Engine.getEngine().getEntityStream().getEntities(EntityType.ENEMY);
        double minDist = 0;
        Entity closest = null;
        for (Entity e: enemies){
            double dis = distanceTo(e);
            if (minDist == 0 || dis < minDist){
                minDist = dis;
                closest = e;
            }
        }
        if (closest != null) {
            double rotToGet = Math.atan2(x - closest.getX(),y - closest.getY());
            if (rotToGet < 0){
                rotToGet += 2*Math.PI;
            }
            if (Math.abs(rot - rotToGet) > Math.abs(rot - rotToGet - 0.1)){
                rot -= 0.15;
            }
            else {
                rot += 0.15;
            }
        }
        super.update();
    }
}
