package code.components.behaviour.mouse;

import code.Constants;
import code.components.ComponentType;
import code.components.item.ItemComponent;
import code.components.primary.PrimaryComponent;
import code.components.secondary.SecondaryComponent;
import code.components.storage.change.ChangeStorage;
import code.components.storage.move.MoveStorage;
import code.components.storage.status.StatusStorage;
import code.components.storage.transformation.TransformationStorage;
import code.engine.Engine;
import code.entity.Entity;
import code.input.MouseWrapper;
import code.math.VectorD;
import code.util.EntityUtil;
import java.util.List;

public class PlayerMouseHandler extends MouseHandler {
    public PlayerMouseHandler() {
        super(MouseHandlerType.PLAYER);
    }

    public void handle(Entity entity, MouseWrapper mouse) {
        StatusStorage statStore = (StatusStorage) entity.getComponent(ComponentType.STATUS);

        if (statStore.canShoot()) {
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

            chngStore.setRotationChange(
                    EntityUtil.calculateDeltaRot(rotChange, mvStore.getTurnRate()));


            // TODO this will move into mouse handler on item itself
            if (mouse.getMouse1().isDown()) {
                PrimaryComponent prc =
                        (PrimaryComponent) entity.getComponent(ComponentType.PRIMARY);
                List<Entity> items = prc.getItems();
                for (Entity item : items) {
                    ItemComponent ic = (ItemComponent) item.getComponent(ComponentType.ITEM);
                    ic.activate(item);
                }
            }
            if (mouse.getMouse2().isDown()) {
                SecondaryComponent src =
                        (SecondaryComponent) entity.getComponent(ComponentType.SECONDARY);
                List<Entity> items = src.getItems();
                for (Entity item : items) {
                    ItemComponent ic = (ItemComponent) item.getComponent(ComponentType.ITEM);
                    ic.activate(item);
                }
            }
        }
    }
}
