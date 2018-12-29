package code.system;

import code.collision.HitShape;
import code.components.ComponentType;
import code.components.collision.CollisionComponent;
import code.components.item.ItemComponent;
import code.components.link.LinkComponent;
import code.components.position.PositionComponent;
import code.components.inventory.InventoryComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.engine.SystemType;
import code.math.MatrixD;
import code.util.MatrixUtil;

import java.util.Set;

/**
 * Created by theo on 24/06/17.
 */
public class EquipSystem extends GameSystem {
    public EquipSystem() {
        super(SystemType.EQUIP);
    }

    public void update() {
        Set<NEntity> entities = Engine.getEngine().getNEntityStream().getEntities(ComponentType.EQUIP);

        NEntity player = Engine.getEngine().getNEntityStream().getPlayer();

        for (NEntity entity : entities) {
            CollisionComponent cc = (CollisionComponent) entity.getComponent(ComponentType.COLLISION);
            PositionComponent pc = (PositionComponent) entity.getComponent(ComponentType.POSITION);
            MatrixD eTransform = MatrixUtil.getTransRotationMatrix(pc.getCoord(), pc.getRot());
            HitShape updated = cc.getHitShape().update(eTransform);

            CollisionComponent ccc = (CollisionComponent) player.getComponent(ComponentType.COLLISION);
            PositionComponent cpc = (PositionComponent) player.getComponent(ComponentType.POSITION);
            MatrixD cTransform = MatrixUtil.getTransRotationMatrix(cpc.getCoord(), cpc.getRot());
            if (ccc.getHitShape().update(cTransform).collision(updated)) {
                // remove render, equip, position, age, velocity
                entity.removeComponent(ComponentType.RENDER);
                entity.removeComponent(ComponentType.EQUIP);
                entity.removeComponent(ComponentType.POSITION);
                entity.removeComponent(ComponentType.AGE);
                entity.removeComponent(ComponentType.VELOCITY);
                entity.removeComponent(ComponentType.AI);
                // add to inventory
                ItemComponent ic = (ItemComponent) entity.getComponent(ComponentType.ITEM);
                switch (ic.getItemType()) {
                case SHIP:
                    InventoryComponent sc = (InventoryComponent) player.getComponent(ComponentType.SHIP);
                    sc.addItem(entity);
                    break;
                case PRIMARY:
                    InventoryComponent prc = (InventoryComponent) player.getComponent(ComponentType.PRIMARY);
                    prc.addItem(entity);
                    break;
                case SECONDARY:
                    InventoryComponent sdc = (InventoryComponent) player.getComponent(ComponentType.SECONDARY);
                    sdc.addItem(entity);
                    break;
                default:
                    break;
                }
                entity.addComponent(new LinkComponent(player));
            }
        }
    }
}
