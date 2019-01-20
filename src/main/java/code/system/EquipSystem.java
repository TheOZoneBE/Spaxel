package code.system;

import code.collision.HitShape;
import code.components.ComponentType;
import code.components.storage.hitshape.HitshapeStorage;
import code.components.item.ItemComponent;
import code.components.storage.transformation.TransformationStorage;
import code.components.inventory.InventoryComponent;
import code.engine.Engine;
import code.entity.Entity;
import code.math.MatrixD;
import code.util.MatrixUtil;
import java.util.Set;

/**
 * The EquipSystem is responsible for updating all entities with an EquipComponent
 * 
 * Created by theo on 24/06/17.
 */
public class EquipSystem extends GameSystem {
    /**
     * Create a new EquipSystem
     */
    public EquipSystem() {
        super(SystemType.EQUIP);
    }

    public void update() {
        Set<Entity> entities = Engine.get().getNEntityStream().getEntities(ComponentType.EQUIP);

        Entity player = Engine.get().getNEntityStream().getPlayer();

        for (Entity entity : entities) {
            HitshapeStorage cc = (HitshapeStorage) entity.getComponent(ComponentType.HITSHAPE);
            TransformationStorage pc =
                    (TransformationStorage) entity.getComponent(ComponentType.TRANSFORMATION);
            MatrixD eTransform =
                    MatrixUtil.getTransRotationMatrix(pc.getPosition(), pc.getRotation());
            HitShape updated = cc.getHitShape().update(eTransform);

            HitshapeStorage ccc = (HitshapeStorage) player.getComponent(ComponentType.HITSHAPE);
            TransformationStorage cpc =
                    (TransformationStorage) player.getComponent(ComponentType.TRANSFORMATION);
            MatrixD cTransform =
                    MatrixUtil.getTransRotationMatrix(cpc.getPosition(), cpc.getRotation());
            if (ccc.getHitShape().update(cTransform).collision(updated)) {
                // remove render, equip, position, age, velocity
                entity.removeComponent(ComponentType.RENDER);
                entity.removeComponent(ComponentType.EQUIP);
                entity.removeComponent(ComponentType.TRANSFORMATION);
                entity.removeComponent(ComponentType.AGE);
                entity.removeComponent(ComponentType.CHANGE);
                entity.removeComponent(ComponentType.AI);
                // add to inventory
                ItemComponent ic = (ItemComponent) entity.getComponent(ComponentType.ITEM);
                switch (ic.getItemType()) {
                    case SHIP:
                        InventoryComponent sc =
                                (InventoryComponent) player.getComponent(ComponentType.SHIP);
                        sc.addItem(entity);
                        break;
                    case PRIMARY:
                        InventoryComponent prc =
                                (InventoryComponent) player.getComponent(ComponentType.PRIMARY);
                        prc.addItem(entity);
                        break;
                    case SECONDARY:
                        InventoryComponent sdc =
                                (InventoryComponent) player.getComponent(ComponentType.SECONDARY);
                        sdc.addItem(entity);
                        break;
                    default:
                        break;
                }
                player.addLink(entity);
            }
        }
    }
}
