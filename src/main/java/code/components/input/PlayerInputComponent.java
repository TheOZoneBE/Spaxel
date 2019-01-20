package code.components.input;

import code.components.ComponentType;
import code.components.Component;
import code.components.actor.ActorComponent;
import code.components.item.ItemComponent;
import code.components.move.MoveComponent;
import code.components.position.PositionComponent;
import code.components.primary.PrimaryComponent;
import code.components.secondary.SecondaryComponent;
import code.components.storage.change.ChangeStorage;
import code.engine.Engine;
import code.entity.Entity;
import code.input.Keyboard;
import code.input.Key;
import code.input.MouseWrapper;
import code.math.VectorD;
import code.Constants;
import code.util.EntityUtil;
import java.util.List;

/**
 * Created by theo on 21/06/17.
 */
public class PlayerInputComponent extends InputComponent {
    private static final int SPEED_MULT = 2;

    public PlayerInputComponent() {
        super(InputType.PLAYER);
    }

    public void update(Entity entity) {
        ActorComponent ac = (ActorComponent) entity.getComponent(ComponentType.ACTOR);

        if (ac.canMove()) {
            handleMoving(entity);
        }

        if (ac.canShoot()) {
            handleShooting(entity);
        }
    }

    private static void handleMoving(Entity entity) {
        Keyboard keys = Engine.get().getKeyboard();
        MouseWrapper mouse = Engine.get().getMouseWrapper();
        ChangeStorage vc = (ChangeStorage) entity.getComponent(ComponentType.CHANGE);
        MoveComponent mc = (MoveComponent) entity.getComponent(ComponentType.MOVE);
        PositionComponent pc = (PositionComponent) entity.getComponent(ComponentType.POSITION);

        VectorD velChange =
                vc.getPositionChange().multiplicate(-1 / (mc.getMaxSpeed() * SPEED_MULT));
        if (keys.get(Key.DOWN).isDown()) {
            velChange = new VectorD(-Math.sin(pc.getRot()), -Math.cos(pc.getRot()))
                    .multiplicate(mc.getAcc());
        }
        if (keys.get(Key.UP).isDown()) {
            velChange = new VectorD(Math.sin(pc.getRot()), Math.cos(pc.getRot()))
                    .multiplicate(mc.getAcc());
        }
        if (keys.get(Key.LEFT).isDown()) {
            velChange = new VectorD(Math.sin(pc.getRot() - Constants.HALF_CIRLCE),
                    Math.cos(pc.getRot() - Constants.HALF_CIRLCE)).multiplicate(mc.getAcc());
        }

        if (keys.get(Key.RIGHT).isDown()) {
            velChange = new VectorD(Math.sin(pc.getRot() + Constants.HALF_CIRLCE),
                    Math.cos(pc.getRot() + Constants.HALF_CIRLCE)).multiplicate(mc.getAcc());
        }
        if (vc.getPositionChange().sum(velChange).length() < mc.getMaxSpeed()) {
            vc.setPositionChange(vc.getPositionChange().sum(velChange));
        } else {
            vc.setPositionChange(vc.getPositionChange().sum(
                    vc.getPositionChange().multiplicate(-1 / (mc.getMaxSpeed() * SPEED_MULT))));
        }

        VectorD mousePos = mouse.getPos();

        VectorD diff = mousePos.sum(
                pc.getCoord().sum(Engine.get().getGameState().getScreenOffset()).multiplicate(-1));
        double rotToGet = diff.angle();

        if (rotToGet < 0) {
            rotToGet += Constants.FULL_CIRCLE;
        }
        double rotChange = rotToGet - pc.getRot();

        vc.setRotationChange(EntityUtil.calculateDeltaRot(rotChange, mc.getTurnRate()));
    }

    private static void handleShooting(Entity entity) {
        MouseWrapper mouse = Engine.get().getMouseWrapper();
        if (mouse.getMouse1().isDown()) {
            PrimaryComponent prc = (PrimaryComponent) entity.getComponent(ComponentType.PRIMARY);
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

    public Component copy() {
        return new PlayerInputComponent();
    }
}
