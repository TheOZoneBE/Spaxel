package code.system;

import code.components.ComponentType;
import code.components.storage.transformation.TransformationStorage;
import code.components.storage.change.ChangeStorage;
import code.engine.Engine;
import code.entity.Entity;
import code.system.SystemType;
import java.util.Set;

/**
 * The velocity system is responsible for updating the position and rotationof the entities based on
 * their current velocity
 * 
 * Created by theo on 4/06/17.
 */
public class VelocitySystem extends GameSystem {
    /**
     * Creates a new VelocitySystem
     */
    public VelocitySystem() {
        super(SystemType.VELOCITY);
    }

    public void update() {
        Set<Entity> nEntities = Engine.get().getNEntityStream().getEntities(ComponentType.CHANGE);
        for (Entity ne : nEntities) {
            TransformationStorage pc =
                    (TransformationStorage) ne.getComponent(ComponentType.TRANSFORMATION);
            ChangeStorage vc = (ChangeStorage) ne.getComponent(ComponentType.CHANGE);
            pc.setPosition(pc.getPosition().sum(vc.getPositionChange()));
            pc.setRotation(pc.getRotation() + vc.getRotationationChange());
        }
    }
}
