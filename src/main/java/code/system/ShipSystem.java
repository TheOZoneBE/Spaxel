package code.system;

import code.components.ComponentType;
import code.components.item.ItemComponent;
import code.components.ship.ShipComponent;
import code.engine.Engine;
import code.entity.Entity;
import code.system.SystemType;
import java.util.Set;

/**
 * The ShipSystem is responsible for updating the entities with a ShipComponent
 * 
 * Created by theo on 8/07/17.
 */
public class ShipSystem extends GameSystem {
    /**
     * Create a new ShipSystem
     */
    public ShipSystem() {
        super(SystemType.SHIP);
    }

    public void update() {
        Set<Entity> entities =
                Engine.get().getNEntityStream().getEntities(ComponentType.SHIP);
        for (Entity entity : entities) {
            ShipComponent sc = (ShipComponent) entity.getComponent(ComponentType.SHIP);
            for (Entity item : sc.getItems()) {
                ItemComponent ic = (ItemComponent) item.getComponent(ComponentType.ITEM);
                ic.activate(item);
            }
        }
    }
}
