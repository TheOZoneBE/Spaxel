package code.system;

import code.components.ComponentType;
import code.components.PositionComponent;
import code.components.VelocityComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.engine.SystemType;

import java.util.Set;

/**
 * Created by theo on 4/06/17.
 */
public class VelocitySystem extends GameSystem {
    public VelocitySystem() {
        super(SystemType.VELOCITY);
    }

    public void update(){
        Set<NEntity> nEntities = Engine.getEngine().getNEntityStream().getEntities(ComponentType.VELOCITY);
        for (NEntity ne: nEntities){
            PositionComponent pc = (PositionComponent)ne.getComponent(ComponentType.POSITION);
            VelocityComponent vc = (VelocityComponent)ne.getComponent(ComponentType.VELOCITY);
            pc.setCoord(pc.getCoord().sum(vc.getVelocity()));
            pc.setRot(pc.getRot() + vc.getDeltaRot());
        }
    }
}
