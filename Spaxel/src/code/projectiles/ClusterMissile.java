package code.projectiles;

import code.engine.Engine;
import code.engine.EntityType;
import code.entity.Entity;
import code.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by theo on 11-5-2016.
 */
public class ClusterMissile extends Projectile {

    public ClusterMissile(double x, double y, double rot, Sprite sprite, int damage, int life, double speed) {
        super(x, y, rot, sprite, damage, life, speed);
    }

    public void update(){
        super.update();
        if (!alive){
            Random rand = new Random();
            List<Entity> schrapnel = new ArrayList<>();
            for (int i = 0; i< 5; i++){
                schrapnel.add(new BasicMissile(x, y,rand.nextDouble() * 2 * Math.PI, sprite, damage/5, 50, speed/2));
            }
            Engine.getEngine().getEntityStream().addEntities(EntityType.PROJECTILE, schrapnel);
        }
    }


}
