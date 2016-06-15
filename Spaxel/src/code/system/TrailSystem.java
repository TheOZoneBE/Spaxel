package code.system;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.SystemType;
import code.entity.Entity;
import code.projectiles.Projectile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by theo on 19-5-2016.
 */
public class TrailSystem extends GameSystem {

    public TrailSystem() {
        super(SystemType.TRAIL);
    }

    public void update(){
        Iterator<Entity> entities = Engine.getEngine().getEntityStream().getIterator(EntityType.PLAYER_PROJECTILE);
        List<Entity> trailsegments = new ArrayList<>();
        while(entities.hasNext()){
            Projectile e = (Projectile) entities.next();
            trailsegments.add(e.leaveTrail());
        }
        entities = Engine.getEngine().getEntityStream().getIterator(EntityType.ENEMY_PROJECTILE);
        while(entities.hasNext()){
            Projectile e = (Projectile) entities.next();
            trailsegments.add(e.leaveTrail());
        }
        Engine.getEngine().getEntityStream().addEntities(EntityType.TRAILSEGMENT, trailsegments);
    }
}
