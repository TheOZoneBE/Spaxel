package code.components.ai;

import code.components.Component;
import code.components.ComponentType;
import code.components.velocity.VelocityComponent;
import code.entity.Entity;

/**
 * Created by theod on 11-7-2017.
 */
public class DroppedItemAIComponent extends AIComponent {
    private static final double HALF = 0.5D;

    public DroppedItemAIComponent() {
        super(AIType.DROPPED_ITEM);
    }

    public void execute(Entity entity) {
        VelocityComponent vc = (VelocityComponent) entity.getComponent(ComponentType.VELOCITY);
        vc.setVelocity(vc.getVelocity().multiplicate(HALF));
    }

    public Component copy(){
        return new DroppedItemAIComponent();
    }
}
