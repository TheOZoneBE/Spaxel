package code.components.affect;

import code.components.actor.ActorComponent;
import code.components.Component;
import code.components.ComponentType;
import code.engine.NEntity;

/**
 * Created by theod on 28-6-2017.
 */
public class DisableShootAffectComponent extends AffectComponent {
    public DisableShootAffectComponent() {
        super(AffectType.DISABLE_SHOOT, 0);
    }

    public void affect(NEntity entity, NEntity victim){
        ActorComponent ac = (ActorComponent)victim.getComponent(ComponentType.ACTOR);
        ac.setCanShoot(false);
    }

    public Component copy(){
        return new DisableShootAffectComponent();
    }
}
