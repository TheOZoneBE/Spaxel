package code.projectiles;

import code.engine.Engine;
import code.engine.EntityType;
import code.entity.Entity;
import code.graphics.SpriteData;

import java.util.Iterator;

/**
 * Created by theo on 13-5-2016.
 */
public class HomingMissile extends Projectile {
    public HomingMissile(float x, float y, float rot, SpriteData sprite, SpriteData trail, int damage, int life, float speed) {
        super(x, y, rot, sprite,trail, damage, life, speed);
    }

    public void update(){
        Iterator<Entity> enemies = Engine.getEngine().getEntityStream().getIterator(EntityType.ENEMY);
        float minDist = 0;
        Entity closest = null;
        while (enemies.hasNext()){
            Entity e = enemies.next();
            float dis = distanceTo(e);
            if (minDist == 0 || dis < minDist){
                minDist = dis;
                closest = e;
            }

        }
        if (closest != null) {
            float rotToGet = (float) Math.atan2(x - closest.getX(),y - closest.getY());
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
