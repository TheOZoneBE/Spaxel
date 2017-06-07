package code.components;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.math.VectorF;

import javax.swing.text.html.parser.Entity;
import java.util.Set;

/**
 * Created by theo on 7/06/17.
 */
public class BasicEnemyAIComponent extends AIComponent {


    public BasicEnemyAIComponent(){
        super(AIType.BASICENEMY);
    }

    public void execute(PositionComponent playerPos, NEntity entity){
        //TODO effectComponent
        /*
        List<StatusEffect> todelete = new ArrayList<>();
        synchronized (effects){
            for (StatusEffect e : effects) {
                e.update();
                if (!e.isAlive()) {
                    e.undo(this);
                    todelete.add(e);
                }
            }
            effects.removeAll(todelete);
        }*/

        //TODO healthcomponent
        /*if (health < 0) {
            alive = false;
        } else {
            */
        PositionComponent entityPos = (PositionComponent)entity.getComponent(ComponentType.POSITION);
        MoveComponent entityMov = (MoveComponent)entity.getComponent(ComponentType.MOVE);
        VelocityComponent entityVel = (VelocityComponent)entity.getComponent(ComponentType.VELOCITY);
        VectorF diff = playerPos.getCoord().sum(entityPos.getCoord().multiplicate(-1));
        float rotToGet = (float)(Math.atan2(diff.getValue(0), diff.getValue(1)));
        if (rotToGet < 0){
            rotToGet += 2*Math.PI;
        }
        float rotChange = rotToGet - entityPos.getRot();
        if (Math.abs(rotChange) < entityMov.getTurnRate()){
            entityVel.setDeltaRot(rotChange);
        }
        else if (rotChange < 0){
            if (rotChange < -Math.PI){
                entityVel.setDeltaRot(entityMov.getTurnRate());
            }
            else {
                entityVel.setDeltaRot(-entityMov.getTurnRate());
            }
        }
        else {
            if (rotChange > Math.PI){
                entityVel.setDeltaRot(-entityMov.getTurnRate());
            }
            else {
                entityVel.setDeltaRot(entityMov.getTurnRate());
            }
        }
        //float dx = 0;
        //float dy = 0;

        VectorF velChange = new VectorF((float)Math.sin(entityPos.getRot()), (float)Math.cos(entityPos.getRot())).multiplicate(entityMov.getAcc());
        //dx =(float)-Math.sin(rot) * acc;
        //dy = (float)-Math.cos(rot) * acc;

        float dist = playerPos.getCoord().sum(entityPos.getCoord().multiplicate(-1)).length();
        if (dist > 250) {
            if (entityVel.getVelocity().sum(velChange).length() < entityMov.getMaxSpeed()) {
                entityVel.setVelocity(entityVel.getVelocity().sum(velChange));
            } else {
                entityVel.setVelocity(
                        entityVel.getVelocity()
                                .sum(entityVel.getVelocity()
                                        .multiplicate(-1/entityMov.getMaxSpeed()*2))
                                .sum(velChange));

                //xdir = xdir - xdir / (maxspeed * 2) + dx;
                //ydir = ydir - ydir / (maxspeed * 2) + dy;
            }
        } else {
            entityVel.setVelocity(entityVel.getVelocity().sum(velChange.multiplicate(-1)));
        }
        //TODO primary, secondary, ship items components
        /*
        if (canshoot && cooldown == 0) {
            Engine.getEngine().getEntityStream().addEntity(EntityType.ENEMY_PROJECTILE, new BasicLaser(x, y, rot, Engine.getEngine().getSpriteAtlas().get("basic_laser_projectile"), Engine.getEngine().getSpriteAtlas().get("white_trail"), 5, 100, 20));
            cooldown =100;
        }

        cooldown--;*/
    }
}
