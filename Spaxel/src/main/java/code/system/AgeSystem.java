package code.system;

import code.components.age.AgeComponent;
import code.components.ComponentType;
import code.engine.Engine;
import code.engine.NEntity;
import code.engine.SystemType;

import java.util.Set;

/**
 * Created by theo on 4/06/17.
 */
public class AgeSystem extends GameSystem {

    public AgeSystem() {
        super(SystemType.AGE);
    }

    public void update(){
        Set<NEntity> nEntities = Engine.getEngine().getNEntityStream().getEntities(ComponentType.AGE);
        for (NEntity ne: nEntities){
            AgeComponent ac = (AgeComponent)ne.getComponent(ComponentType.AGE);
            if (ac.getLife() != 0){
                ac.setLife(ac.getLife() - 1);
            }
            else{
                Engine.getEngine().getNEntityStream().removeEntity(ne);
            }
        }
    }
}
