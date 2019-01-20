package code.components.behaviour.keyboard;

import code.Constants;
import code.components.ComponentType;
import code.components.storage.change.ChangeStorage;
import code.components.storage.move.MoveStorage;
import code.components.storage.status.StatusStorage;
import code.components.storage.transformation.TransformationStorage;
import code.entity.Entity;
import code.input.Key;
import code.input.Keyboard;
import code.math.VectorD;

public class PlayerKeyboardHandler extends KeyboardHandler {
    public PlayerKeyboardHandler() {
        super(KeyboardHandlerType.PLAYER);
    }

    public void handle(Entity entity, Keyboard keyboard) {
        StatusStorage statStore = (StatusStorage) entity.getComponent(ComponentType.STATUS);

        if (statStore.canMove()) {
            ChangeStorage chngStore = (ChangeStorage) entity.getComponent(ComponentType.CHANGE);
            MoveStorage mvStore = (MoveStorage) entity.getComponent(ComponentType.MOVE);
            TransformationStorage trnsStore =
                    (TransformationStorage) entity.getComponent(ComponentType.TRANSFORMATION);

            VectorD velChange = chngStore.getPositionChange()
                    .multiplicate(-1 / (mvStore.getSpeed() * Constants.SPEED_MULT));
            if (keyboard.get(Key.DOWN).isDown()) {
                velChange = new VectorD(-Math.sin(trnsStore.getRotation()),
                        -Math.cos(trnsStore.getRotation())).multiplicate(mvStore.getAcceleration());
            }
            if (keyboard.get(Key.UP).isDown()) {
                velChange = new VectorD(Math.sin(trnsStore.getRotation()),
                        Math.cos(trnsStore.getRotation())).multiplicate(mvStore.getAcceleration());
            }
            if (keyboard.get(Key.LEFT).isDown()) {
                velChange = new VectorD(Math.sin(trnsStore.getRotation() - Constants.HALF_CIRLCE),
                        Math.cos(trnsStore.getRotation() - Constants.HALF_CIRLCE))
                                .multiplicate(mvStore.getAcceleration());
            }

            if (keyboard.get(Key.RIGHT).isDown()) {
                velChange = new VectorD(Math.sin(trnsStore.getRotation() + Constants.HALF_CIRLCE),
                        Math.cos(trnsStore.getRotation() + Constants.HALF_CIRLCE))
                                .multiplicate(mvStore.getAcceleration());
            }
            if (chngStore.getPositionChange().sum(velChange).length() < mvStore.getSpeed()) {
                chngStore.setPositionChange(chngStore.getPositionChange().sum(velChange));
            } else {
                chngStore.setPositionChange(chngStore.getPositionChange().sum(chngStore
                        .getPositionChange().multiplicate(-1 / (mvStore.getSpeed() * Constants.SPEED_MULT))));
            }
        }
    }
}
