package code.system;

import code.components.ComponentType;
import code.components.item.ItemComponent;
import code.components.ship.ShipComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.engine.SystemType;

import java.util.Set;

/**
 * Created by theo on 8/07/17.
 */
public class ShipSystem extends GameSystem {
    public ShipSystem() {
        super(SystemType.SHIP);
    }

    public void update(){
        Set<NEntity> entities = Engine.getEngine().getNEntityStream().getEntities(ComponentType.SHIP);
        for (NEntity entity: entities){
            ShipComponent sc = (ShipComponent)entity.getComponent(ComponentType.SHIP);
            for (NEntity item: sc.getItems()){
                ItemComponent ic = (ItemComponent)item.getComponent(ComponentType.ITEM);
                ic.activate(item);
            }
        }
    }
}
