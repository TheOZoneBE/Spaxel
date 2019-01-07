package code.system;

import code.components.ComponentType;
import code.components.marker.MarkerComponent;
import code.engine.Engine;
import code.entity.Entity;
import code.system.SystemType;
import java.util.Set;

/**
 * The MarkerSystem is responsible for updating the entities with a MarkerComponent
 * 
 * Created by theo on 4/01/18.
 */
public class MarkerSystem extends GameSystem {
    /**
     * Create a new MarkerSystem
     */
    public MarkerSystem() {
        super(SystemType.MARKER);
    }

    public void update() {
        Set<Entity> markers =
                Engine.get().getNEntityStream().getEntities(ComponentType.MARKER);
        for (Entity marker : markers) {
            MarkerComponent mc = (MarkerComponent) marker.getComponent(ComponentType.MARKER);
            mc.update(marker);
        }
    }
}
