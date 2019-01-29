package code.components.behaviour.hit;

import code.components.ComponentType;
import code.entity.Entity;
import code.entity.EntityType;

/**
 * Created by theo on 24/06/17.
 */
public class EquipHandler extends HitHandler {
    public EquipHandler() {
        super(HitType.EQUIP);
    }

    public void hit(Entity entity, Entity victim) {
        if (victim.getType() == EntityType.PLAYER) {
            // remove render, hit, transformation, age, velocity, ai
            entity.removeComponent(ComponentType.RENDER);
            entity.removeComponent(ComponentType.HIT);
            entity.removeComponent(ComponentType.TRANSFORMATION);
            entity.removeComponent(ComponentType.AGE);
            entity.removeComponent(ComponentType.CHANGE);
            entity.removeComponent(ComponentType.AI);

            // TODO figure out stacking behaviour
            victim.addLink(entity);
        }
    }
}
