package code.system;

import code.collision.HitShape;
import code.components.ComponentType;
import code.components.collision.CollisionComponent;
import code.components.hit.HitComponent;
import code.components.item.ItemComponent;
import code.components.link.LinkComponent;
import code.components.position.PositionComponent;
import code.components.primary.PrimaryComponent;
import code.components.secondary.SecondaryComponent;
import code.components.ship.ShipComponent;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.engine.SystemType;
import code.math.MatrixF;
import code.math.MatrixMaker;

import java.util.Set;

/**
 * Created by theo on 24/06/17.
 */
public class EquipSystem extends GameSystem {
    public EquipSystem() {
        super(SystemType.EQUIP);
    }

    public void update(){
        Set<NEntity> entities = Engine.getEngine().getNEntityStream().getEntities(ComponentType.EQUIP);
        //TODO decide whether enemies can pickup items
        Set<NEntity> colliders = Engine.getEngine().getNEntityStream().getEntities(EntityType.PLAYER);
        for (NEntity entity: entities){
            CollisionComponent cc = (CollisionComponent)entity.getComponent(ComponentType.COLLISION);
            PositionComponent pc = (PositionComponent)entity.getComponent(ComponentType.POSITION);
            MatrixF eTransform = MatrixMaker.getTransformationMatrix(pc.getCoord(), pc.getRot(), 1);
            HitShape updated = cc.getHitShape().update(eTransform);
            for (NEntity collider: colliders){
                CollisionComponent ccc = (CollisionComponent)collider.getComponent(ComponentType.COLLISION);
                PositionComponent cpc = (PositionComponent)collider.getComponent(ComponentType.POSITION);
                MatrixF cTransform = MatrixMaker.getTransformationMatrix(cpc.getCoord(), cpc.getRot(), 1);
                if(ccc.getHitShape().update(cTransform).collision(updated)){
                    //remove render, equip, position, age, velocity
                    entity.removeComponent(ComponentType.RENDER);
                    entity.removeComponent(ComponentType.EQUIP);
                    entity.removeComponent(ComponentType.POSITION);
                    entity.removeComponent(ComponentType.AGE);
                    entity.removeComponent(ComponentType.VELOCITY);
                    //add to inventory
                    ItemComponent ic = (ItemComponent)entity.getComponent(ComponentType.ITEM);
                    switch(ic.getItemType()){
                        case SHIP:
                            ShipComponent sc = (ShipComponent)collider.getComponent(ComponentType.SHIP);
                            sc.addItem(entity);
                            break;
                        case PRIMARY:
                            PrimaryComponent prc = (PrimaryComponent)collider.getComponent(ComponentType.PRIMARY);
                            prc.addItem(entity);
                            break;
                        case SECONDARY:
                            SecondaryComponent sdc = (SecondaryComponent)collider.getComponent(ComponentType.SECONDARY);
                            sdc.addItem(entity);
                            break;
                    }
                    entity.addComponent(new LinkComponent(collider));
                }
            }
        }
    }
}
