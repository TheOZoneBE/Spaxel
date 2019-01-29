package code.components.behaviour.mouse;

import code.Constants;
import code.components.ComponentType;
import code.components.behaviour.event.Event;
import code.components.storage.change.ChangeStorage;
import code.components.storage.event.EventStorage;
import code.components.storage.move.MoveStorage;
import code.components.storage.status.StatusStorage;
import code.components.storage.transformation.TransformationStorage;
import code.engine.Engine;
import code.entity.Entity;
import code.input.MouseWrapper;
import code.math.VectorD;
import code.util.EntityUtil;

public class PlayerMouseHandler extends MouseHandler {
    public PlayerMouseHandler() {
        super(MouseHandlerType.PLAYER);
    }

    public void handle(Entity entity, MouseWrapper mouse) {
        StatusStorage statStore = (StatusStorage) entity.getComponent(ComponentType.STATUS);

        TransformationStorage trnsStore =
                (TransformationStorage) entity.getComponent(ComponentType.TRANSFORMATION);
        ChangeStorage chngStore = (ChangeStorage) entity.getComponent(ComponentType.CHANGE);
        MoveStorage mvStore = (MoveStorage) entity.getComponent(ComponentType.MOVE);

        VectorD mousePos = mouse.getPos();

        VectorD diff = mousePos.sum(trnsStore.getPosition()
                .sum(Engine.get().getGameState().getScreenOffset()).multiplicate(-1));
        double rotToGet = diff.angle();

        if (rotToGet < 0) {
            rotToGet += Constants.FULL_CIRCLE;
        }
        double rotChange = rotToGet - trnsStore.getRotation();

        chngStore.setRotationChange(EntityUtil.calculateDeltaRot(rotChange, mvStore.getTurnRate()));

        if (statStore.canShoot()) {
            EventStorage evStore = (EventStorage) entity.getComponent(ComponentType.EVENT_STORE);
            if (mouse.getMouse1().isDown()) {
                evStore.addEvent(Event.PRIMARY_SHOOT);
            }
            if (mouse.getMouse2().isDown()) {
                evStore.addEvent(Event.SECONDARY_SHOOT);
            }
        }
    }
}
