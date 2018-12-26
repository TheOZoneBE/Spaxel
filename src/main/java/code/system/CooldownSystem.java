package code.system;

import code.components.ComponentType;
import code.components.cooldown.CooldownComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.engine.SystemType;

import java.util.Set;

/**
 * Created by theo on 20/06/17.
 */
public class CooldownSystem extends GameSystem {
    public CooldownSystem() {
        super(SystemType.COOLDOWN);
    }

    public void update(){
        Set<NEntity> entities = Engine.getEngine().getNEntityStream().getEntities(ComponentType.COOLDOWN);
        for (NEntity entity: entities){
            CooldownComponent cc = (CooldownComponent)entity.getComponent(ComponentType.COOLDOWN);
            cc.setCd(cc.getCd() == 0? 0 : cc.getCd() -1);
        }
    }
}
