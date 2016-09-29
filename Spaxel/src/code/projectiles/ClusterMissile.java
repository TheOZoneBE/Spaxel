package code.projectiles;

import code.engine.Engine;
import code.engine.EntityType;
import code.entity.Entity;
import code.graphics.SpriteData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by theo on 11-5-2016.
 */
public class ClusterMissile extends Projectile {

    public ClusterMissile(float x, float y, float rot, SpriteData sprite, SpriteData trail, int damage, int life, float speed) {
        super(x, y, rot, sprite, trail, damage, life, speed);
    }

    public void update(){
        super.update();
        if (life == 1){
            Random rand = new Random();
            List<Entity> schrapnel = new ArrayList<>();
            for (int i = 0; i< 5; i++){
                schrapnel.add(new BasicMissile(x, y,rand.nextFloat() * 2 * (float)Math.PI, sprite,trail, damage/5, 50, speed/2));
            }
            Engine.getEngine().getEntityStream().addEntities(EntityType.PLAYER_PROJECTILE, schrapnel);
        }
    }


}
