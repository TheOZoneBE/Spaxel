package code.components.death.effect;

import code.components.actor.ActorComponent;
import code.components.ComponentType;
import code.components.death.DeathComponent;
import code.components.death.DeathType;
import code.components.effect.EffectComponent;
import code.components.link.LinkComponent;
import code.engine.NEntity;

/**
 * Created by theod on 28-6-2017.
 */
public class DisableShootAffectDeathComponent extends DeathComponent {
    public DisableShootAffectDeathComponent() {
        super(DeathType.DISABLE_SHOOT_AFFECT);
    }

    public void die(NEntity entity){
        NEntity parent = ((LinkComponent)entity.getComponent(ComponentType.LINK)).getLink();
        ActorComponent mc = (ActorComponent)parent.getComponent(ComponentType.ACTOR);
        mc.setCanShoot(true);
        EffectComponent ec = (EffectComponent)parent.getComponent(ComponentType.EFFECT);
        ec.getItems().remove(entity);
    }
}
