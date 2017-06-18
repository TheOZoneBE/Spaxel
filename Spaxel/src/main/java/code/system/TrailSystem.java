package code.system;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.engine.SystemType;
import code.entity.Entity;
import code.projectiles.Projectile;

import java.util.*;

/**
 * Created by theo on 19-5-2016.
 */
public class TrailSystem extends GameSystem {

    public TrailSystem() {
        super(SystemType.TRAIL);
    }

    public void update(){
        Iterator<Entity> entities = Engine.getEngine().getEntityStream().getIterator(EntityType.PLAYER_PROJECTILE);
        //List<Entity> trailsegments = new ArrayList<>();
        List<NEntity> trailsegments = new ArrayList<>();
        while(entities.hasNext()){
            Projectile e = (Projectile) entities.next();
            trailsegments.add(e.leaveTrail());
        }
        entities = Engine.getEngine().getEntityStream().getIterator(EntityType.PROJECTILE);
        while(entities.hasNext()){
            Projectile e = (Projectile) entities.next();
            trailsegments.add(e.leaveTrail());
        }
        Engine.getEngine().getNEntityStream().addEntities(EntityType.TRAILSEGMENT, trailsegments);
    }
}
