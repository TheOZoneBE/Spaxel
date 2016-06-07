package code.system;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.SystemType;
import code.entity.Entity;

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
        Iterator<Entity> entities = Engine.getEngine().getEntityStream().getIterator(EntityType.TRAILSEGMENT);
        while(entities.hasNext()){
            Entity e = entities.next();
            e.update();
        }
    }
}
