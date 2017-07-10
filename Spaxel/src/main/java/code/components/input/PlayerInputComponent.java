package code.components.input;

import code.components.ComponentType;
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
import code.input.MouseWrapper;
import code.math.VectorF;
import javafx.geometry.Pos;

import java.util.List;

/**
 * Created by theo on 21/06/17.
 */
public class PlayerInputComponent extends InputComponent {
    public PlayerInputComponent() {
        super(InputType.PLAYER);
    }

    public void update(NEntity entity){
        Keyboard keys = Engine.getEngine().getKeyboard();
        MouseWrapper mouse = Engine.getEngine().getMouseWrapper();
        ActorComponent ac = (ActorComponent)entity.getComponent(ComponentType.ACTOR);
        VelocityComponent vc = (VelocityComponent)entity.getComponent(ComponentType.VELOCITY);
        MoveComponent mc = (MoveComponent)entity.getComponent(ComponentType.MOVE);
        PositionComponent pc = (PositionComponent)entity.getComponent(ComponentType.POSITION);

        if (ac.canMove()){
            VectorF velChange = vc.getVelocity().multiplicate(-1/(mc.getMaxSpeed()*2));
            if (keys.downState.getState()) {
                velChange = new VectorF((float)-Math.sin(pc.getRot()), (float)-Math.cos(pc.getRot())).multiplicate(mc.getAcc());
            }
            if (keys.upState.getState()) {
                velChange = new VectorF((float)Math.sin(pc.getRot()), (float)Math.cos(pc.getRot())).multiplicate(mc.getAcc());
            }
            if (keys.leftState.getState()) {
                velChange = new VectorF((float)Math.sin(pc.getRot()- Math.PI/2), (float)Math.cos(pc.getRot()- Math.PI/2)).multiplicate(mc.getAcc());
            }

            if (keys.rightState.getState()) {
                velChange = new VectorF((float)Math.sin(pc.getRot()+ Math.PI/2), (float)Math.cos(pc.getRot()+ Math.PI/2)).multiplicate(mc.getAcc());
            }
            if (vc.getVelocity().sum(velChange).length() < mc.getMaxSpeed()) {
                vc.setVelocity(vc.getVelocity().sum(velChange));
            } else {
                vc.setVelocity(vc.getVelocity()
                        .sum(vc.getVelocity()
                                .multiplicate(-1/(mc.getMaxSpeed()*2))));
            }

            VectorF mousePos = new VectorF(mouse.getX(), mouse.getY());

            VectorF diff = mousePos.sum(pc.getCoord().sum(Engine.getEngine().getScreenOffset()).multiplicate(-1));
            float rotToGet = (float)(Math.atan2(diff.getValue(0), diff.getValue(1)));

            if (rotToGet < 0){
                rotToGet += 2*Math.PI;
            }
            float rotChange = rotToGet - pc.getRot();
            //TODO see if can solve duplicate
            if (Math.abs(rotChange) < mc.getTurnRate()){
                vc.setDeltaRot(rotChange);
            }
            else if (rotChange < 0){
                if (rotChange < -Math.PI){
                    vc.setDeltaRot(mc.getTurnRate());
                }
                else {
                    vc.setDeltaRot(-mc.getTurnRate());
                }
            }
            else {
                if (rotChange > Math.PI){
                    vc.setDeltaRot(-mc.getTurnRate());
                }
                else {
                    vc.setDeltaRot(mc.getTurnRate());
                }
            }
        }

        if (ac.canShoot()){
            if(mouse.mouse1){
                PrimaryComponent prc = (PrimaryComponent)entity.getComponent(ComponentType.PRIMARY);
                List<NEntity> items = prc.getItems();
                for (NEntity item: items){
                    ItemComponent ic = (ItemComponent)item.getComponent(ComponentType.ITEM);
                    ic.activate(item);
                }
            }
            if(mouse.mouse2){
                SecondaryComponent src = (SecondaryComponent)entity.getComponent(ComponentType.SECONDARY);
                List<NEntity> items = src.getItems();
                for (NEntity item: items){
                    ItemComponent ic = (ItemComponent)item.getComponent(ComponentType.ITEM);
                    ic.activate(item);
                }
            }
        }
    }
}
