package code.components.behaviour.affect;

import code.components.ComponentType;
import code.components.storage.affect.AffectStorage;
import code.components.storage.change.ChangeStorage;
import code.entity.Entity;

/**
 * Created by theod on 28-6-2017.
 */
public class SlowAffectHandler extends AffectHandler {
    public SlowAffectHandler() {
        super(AffectType.SLOW);
    }

    public void affect(Entity entity) {
        ChangeStorage chngStore =
                (ChangeStorage) entity.getParent().getComponent(ComponentType.CHANGE);
        AffectStorage afctStore = (AffectStorage) entity.getComponent(ComponentType.AFFECT_STORE);

        chngStore.setPositionChange(
                chngStore.getPositionChange().multiplicate(afctStore.getFactor()));
    }

}
