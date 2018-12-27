package code.components.ai;

import code.components.actor.ActorComponent;
import code.components.ComponentType;
import code.components.item.ItemComponent;
import code.components.move.MoveComponent;
import code.components.position.PositionComponent;
import code.components.primary.PrimaryComponent;
import code.components.secondary.SecondaryComponent;
import code.components.velocity.VelocityComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.math.VectorF;
import code.Constants;

/**
 * Created by theo on 7/06/17.
 */
public class BasicEnemyAIComponent extends AIComponent {
    private static final int DISTANCE_THRESHOLD = 250;
    private static final int SPEED_MULTIPLIER = 2;

    public BasicEnemyAIComponent() {
        super(AIType.BASIC_ENEMY);
    }

    public void execute(NEntity entity) {
        PositionComponent playerPos = (PositionComponent) Engine.getEngine().getNEntityStream().getPlayer()
                .getComponent(ComponentType.POSITION);

        ActorComponent ac = (ActorComponent) entity.getComponent(ComponentType.ACTOR);
        PositionComponent entityPos = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        MoveComponent entityMov = (MoveComponent) entity.getComponent(ComponentType.MOVE);
        VelocityComponent entityVel = (VelocityComponent) entity.getComponent(ComponentType.VELOCITY);
        VectorF diff = playerPos.getCoord().sum(entityPos.getCoord().multiplicate(-1));
        if (ac.canMove()) {
            float rotToGet = (float) (Math.atan2(diff.getValue(0), diff.getValue(1)));
            if (rotToGet < 0) {
                rotToGet += Constants.FULL_CIRCLE;
            }
            float rotChange = rotToGet - entityPos.getRot();
            if (Math.abs(rotChange) < entityMov.getTurnRate()) {
                entityVel.setDeltaRot(rotChange);
            } else if (rotChange < 0) {
                if (rotChange < -Math.PI) {
                    entityVel.setDeltaRot(entityMov.getTurnRate());
                } else {
                    entityVel.setDeltaRot(-entityMov.getTurnRate());
                }
            } else {
                if (rotChange > Math.PI) {
                    entityVel.setDeltaRot(-entityMov.getTurnRate());
                } else {
                    entityVel.setDeltaRot(entityMov.getTurnRate());
                }
            }

            VectorF velChange = new VectorF((float) Math.sin(entityPos.getRot()), (float) Math.cos(entityPos.getRot()))
                    .multiplicate(entityMov.getAcc());

            float dist = playerPos.getCoord().sum(entityPos.getCoord().multiplicate(-1)).length();
            if (dist > DISTANCE_THRESHOLD) {
                if (entityVel.getVelocity().sum(velChange).length() < entityMov.getMaxSpeed()) {
                    entityVel.setVelocity(entityVel.getVelocity().sum(velChange));
                } else {
                    // TODO needs a rewrite
                    entityVel.setVelocity(entityVel.getVelocity()
                            .sum(entityVel.getVelocity().multiplicate(-1 / entityMov.getMaxSpeed() * SPEED_MULTIPLIER))
                            .sum(velChange));
                }
            } else {
                entityVel.setVelocity(entityVel.getVelocity().sum(velChange.multiplicate(-1)));
            }
        }
        if (ac.canShoot()) {
            PrimaryComponent prc = (PrimaryComponent) entity.getComponent(ComponentType.PRIMARY);
            for (NEntity e : prc.getItems()) {
                ItemComponent ic = (ItemComponent) e.getComponent(ComponentType.ITEM);
                ic.activate(e);
            }
            SecondaryComponent src = (SecondaryComponent) entity.getComponent(ComponentType.SECONDARY);
            for (NEntity e : src.getItems()) {
                ItemComponent ic = (ItemComponent) e.getComponent(ComponentType.ITEM);
                ic.activate(e);
            }
        }
    }
}
