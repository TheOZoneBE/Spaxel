package code.components.input;

import code.components.ComponentType;
import code.components.Component;
import code.components.actor.ActorComponent;
import code.components.item.ItemComponent;
import code.components.move.MoveComponent;
import code.components.position.PositionComponent;
import code.components.primary.PrimaryComponent;
import code.components.secondary.SecondaryComponent;
import code.components.velocity.VelocityComponent;
import code.engine.Engine;
import code.engine.NEntity;
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

    public void update(NEntity entity) {
        ActorComponent ac = (ActorComponent) entity.getComponent(ComponentType.ACTOR);

        if (ac.canMove()) {
            handleMoving(entity);
        }

        if (ac.canShoot()) {
            handleShooting(entity);
        }
    }

    private static void handleMoving(NEntity entity) {
        Keyboard keys = Engine.get().getKeyboard();
        MouseWrapper mouse = Engine.get().getMouseWrapper();
        VelocityComponent vc = (VelocityComponent) entity.getComponent(ComponentType.VELOCITY);
        MoveComponent mc = (MoveComponent) entity.getComponent(ComponentType.MOVE);
        PositionComponent pc = (PositionComponent) entity.getComponent(ComponentType.POSITION);

        VectorD velChange = vc.getVelocity().multiplicate(-1 / (mc.getMaxSpeed() * SPEED_MULT));
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
        if (vc.getVelocity().sum(velChange).length() < mc.getMaxSpeed()) {
            vc.setVelocity(vc.getVelocity().sum(velChange));
        } else {
            vc.setVelocity(vc.getVelocity()
                    .sum(vc.getVelocity().multiplicate(-1 / (mc.getMaxSpeed() * SPEED_MULT))));
        }

        VectorD mousePos = mouse.getPos();

        VectorD diff = mousePos.sum(
                pc.getCoord().sum(Engine.get().getGameState().getScreenOffset()).multiplicate(-1));
        double rotToGet = diff.angle();

        if (rotToGet < 0) {
            rotToGet += Constants.FULL_CIRCLE;
        }
        double rotChange = rotToGet - pc.getRot();

        vc.setDeltaRot(EntityUtil.calculateDeltaRot(rotChange, mc.getTurnRate()));
    }

    private static void handleShooting(NEntity entity) {
        MouseWrapper mouse = Engine.get().getMouseWrapper();
        if (mouse.getMouse1().isDown()) {
            PrimaryComponent prc = (PrimaryComponent) entity.getComponent(ComponentType.PRIMARY);
            List<NEntity> items = prc.getItems();
            for (NEntity item : items) {
                ItemComponent ic = (ItemComponent) item.getComponent(ComponentType.ITEM);
                ic.activate(item);
            }
        }
        if (mouse.getMouse2().isDown()) {
            SecondaryComponent src =
                    (SecondaryComponent) entity.getComponent(ComponentType.SECONDARY);
            List<NEntity> items = src.getItems();
            for (NEntity item : items) {
                ItemComponent ic = (ItemComponent) item.getComponent(ComponentType.ITEM);
                ic.activate(item);
            }
        }
    }

    public Component copy() {
        return new PlayerInputComponent();
    }
}
