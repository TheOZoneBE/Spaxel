package code.system;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Entity;
import code.entity.TrailSegment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theo on 19-5-2016.
 */
public class TrailSystem extends GameSystem {

    public TrailSystem() {
        super(SystemType.TRAIL);
    }

    public void update(){
        List<Entity> entities = Engine.getEngine().getEntityStream().getEntities(EntityType.TRAILSEGMENT);
        for (Entity e: entities){
                e.update();
        }
        Engine.getEngine().getEntityStream().releaseLock(EntityType.TRAILSEGMENT);
    }
}
