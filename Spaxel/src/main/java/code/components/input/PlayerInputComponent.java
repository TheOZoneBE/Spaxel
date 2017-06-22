package code.components.input;

import code.components.ComponentType;
import code.components.move.MoveComponent;
import code.components.position.PositionComponent;
import code.components.velocity.VelocityComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.input.Keyboard;
import code.input.MouseWrapper;
import code.math.VectorF;
import javafx.geometry.Pos;

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
        VelocityComponent vc = (VelocityComponent)entity.getComponent(ComponentType.VELOCITY);
        MoveComponent mc = (MoveComponent)entity.getComponent(ComponentType.MOVE);
        PositionComponent pc = (PositionComponent)entity.getComponent(ComponentType.POSITION);
        VectorF velChange = vc.getVelocity().multiplicate(1/(mc.getMaxSpeed()*2));

        //float dx = -xdir/(maxspeed*2);
        //float dy = -ydir/(maxspeed*2);
        if (keys.downState.getState()) {
            velChange = new VectorF((float)-Math.sin(pc.getRot()), (float)-Math.cos(pc.getRot())).multiplicate(mc.getAcc());
            //dx = (float)-Math.sin(rot) * acc;
            //dy = (float)-Math.cos(rot) * acc;
        }
        if (keys.upState.getState()) {
            velChange = new VectorF((float)Math.sin(pc.getRot()), (float)Math.cos(pc.getRot())).multiplicate(mc.getAcc());
            //dx = (float)Math.sin(rot) * acc;
            //dy = (float)Math.cos(rot) * acc;
        }
        if (keys.leftState.getState()) {
            velChange = new VectorF((float)Math.sin(pc.getRot()+ Math.PI/2), (float)Math.cos(pc.getRot()+ Math.PI/2)).multiplicate(mc.getAcc());
            //dx = (float)Math.sin(rot + Math.PI / 2) * acc;
            //dy = (float)Math.cos(rot + Math.PI / 2) * acc;
        }

        if (keys.rightState.getState()) {
            velChange = new VectorF((float)Math.sin(pc.getRot()- Math.PI/2), (float)Math.cos(pc.getRot()- Math.PI/2)).multiplicate(mc.getAcc());
            //dx = (float)Math.sin(rot - Math.PI / 2) * acc;
            //dy = (float)Math.cos(rot - Math.PI / 2) * acc;
        }
        if (vc.getVelocity().sum(velChange).length() < mc.getMaxSpeed()) {
            vc.setVelocity(vc.getVelocity().sum(velChange));
            //xdir += dx;
            //ydir += dy;
        } else {
            vc.setVelocity(vc.getVelocity()
                    .sum(vc.getVelocity()
                            .multiplicate(-1/(mc.getMaxSpeed()*2))));
            //xdir = xdir - xdir/(maxspeed*2);
            //ydir = ydir - ydir/(maxspeed*2);
        }

        //x+= xdir*Engine.getEngine().getUpdateTime();
        //y+=ydir*Engine.getEngine().getUpdateTime();
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
        //this.mouseX = mouseX;
        //this.mouseY = mouseY;

        //TODO shooting

        //TODO specific death component of player
        /*
        if (health <= 0){
            Engine.getEngine().stopGame();
            UISystem uis = (UISystem) Engine.getEngine().getSystem(SystemType.UI);
            uis.changeUI("game_over");
            Engine.getEngine().setGameState(Engine.GameState.MENU);
        }*/

        //TODO experienceComponent
        /*
        if (xp >= xpToLevel){
            xp-= xpToLevel;
            xpToLevel*=1.5;
            maxHealth*=1.5;
            health = maxHealth;
        }*/

        //TODO update this in ui controller instead
        /*
        UIBar hp_bar = (UIBar) Engine.getEngine().getUIAtlas().get("play").getElement("hp_bar");
        hp_bar.setPercent(health/(float)maxHealth);
        UIBar xp_bar = (UIBar) Engine.getEngine().getUIAtlas().get("play").getElement("xp_bar");
        xp_bar.setPercent(xp/(float)xpToLevel);

        Label hp_label = (Label)Engine.getEngine().getUIAtlas().get("play").getElement("hp_label");
        Label xp_label = (Label)Engine.getEngine().getUIAtlas().get("play").getElement("xp_label");
        hp_label.setText(health + " / " + maxHealth);
        xp_label.setText(xp + " / " + xpToLevel);
        */
    }
}
