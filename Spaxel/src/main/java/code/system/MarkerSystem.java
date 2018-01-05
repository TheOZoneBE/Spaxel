package code.system;

import code.Game;
import code.components.ComponentType;
import code.components.marker.MarkerComponent;
import code.components.position.PositionComponent;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.engine.SystemType;
import code.math.VectorF;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by theo on 4/01/18.
 */
public class MarkerSystem extends GameSystem {
    public MarkerSystem() {
        super(SystemType.MARKER);
    }

    public void update(){
        Set<NEntity> markers = Engine.getEngine().getNEntityStream().getEntities(ComponentType.MARKER);
        for (NEntity marker: markers){
            MarkerComponent mc = (MarkerComponent)marker.getComponent(ComponentType.MARKER);
            mc.update(marker);
        }
    }
}
