package code.components.ai;

import code.Constants;
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
import code.math.VectorD;
import code.util.EntityUtil;

/**
 * Created by theo on 7/06/17.
 */
public class BasicEnemyAIComponent extends AIComponent {
    private static final int DISTANCE_THRESHOLD = 250;
    private static final int SPEED_MULTIPLIER = 2;

    public BasicEnemyAIComponent() {
        super(AIType.BASIC_ENEMY);
    }

    public void execute(Entity entity) {
        PositionComponent playerPos = (PositionComponent) Engine.get().getNEntityStream()
                .getPlayer().getComponent(ComponentType.POSITION);

        ActorComponent ac = (ActorComponent) entity.getComponent(ComponentType.ACTOR);
        PositionComponent entityPos =
                (PositionComponent) entity.getComponent(ComponentType.POSITION);
        MoveComponent entityMov = (MoveComponent) entity.getComponent(ComponentType.MOVE);
        ChangeStorage entityVel = (ChangeStorage) entity.getComponent(ComponentType.CHANGE);
        VectorD diff = playerPos.getCoord().sum(entityPos.getCoord().multiplicate(-1));
        if (ac.canMove()) {
            double rotToGet = Math.atan2(diff.getValue(0), diff.getValue(1));
            if (rotToGet < 0) {
                rotToGet += Constants.FULL_CIRCLE;
            }
            double rotChange = rotToGet - entityPos.getRot();
            entityVel.setRotationChange(
                    EntityUtil.calculateDeltaRot(rotChange, entityMov.getTurnRate()));

            VectorD velChange =
                    new VectorD(Math.sin(entityPos.getRot()), Math.cos(entityPos.getRot()))
                            .multiplicate(entityMov.getAcc());

            double dist = playerPos.getCoord().sum(entityPos.getCoord().multiplicate(-1)).length();
            if (dist > DISTANCE_THRESHOLD) {
                if (entityVel.getPositionChange().sum(velChange).length() < entityMov
                        .getMaxSpeed()) {
                    entityVel.setPositionChange(entityVel.getPositionChange().sum(velChange));
                } else {
                    // TODO needs a rewrite
                    entityVel.setPositionChange(entityVel.getPositionChange()
                            .sum(entityVel.getPositionChange()
                                    .multiplicate(-1 / entityMov.getMaxSpeed() * SPEED_MULTIPLIER))
                            .sum(velChange));
                }
            } else {
                entityVel.setPositionChange(
                        entityVel.getPositionChange().sum(velChange.multiplicate(-1)));
            }
        }
        if (ac.canShoot()) {
            PrimaryComponent prc = (PrimaryComponent) entity.getComponent(ComponentType.PRIMARY);
            for (Entity e : prc.getItems()) {
                ItemComponent ic = (ItemComponent) e.getComponent(ComponentType.ITEM);
                ic.activate(e);
            }
            SecondaryComponent src =
                    (SecondaryComponent) entity.getComponent(ComponentType.SECONDARY);
            for (Entity e : src.getItems()) {
                ItemComponent ic = (ItemComponent) e.getComponent(ComponentType.ITEM);
                ic.activate(e);
            }
        }
    }

    public Component copy() {
        return new BasicEnemyAIComponent();
    }
}
