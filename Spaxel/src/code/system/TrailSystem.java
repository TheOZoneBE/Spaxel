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

    public TrailSystem(Engine engine) {
        super(engine);
        type = SystemType.TRAIL;
    }

    public void update(){
        List<Entity> entities = Engine.getEngine().getEntityStream().getEntities(EntityType.TRAILSEGMENT);
        List<Entity> toRemove = new ArrayList<>();
        for (Entity e: entities){
            e.update();
            if (!((TrailSegment)e).isAlive()){
                toRemove.add(e);
            }
        }
        entities.removeAll(toRemove);
    }
}
