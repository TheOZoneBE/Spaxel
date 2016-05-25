package code.inventory;

import code.engine.Engine;
import code.engine.EntityType;
import code.entity.Entity;
import code.graphics.Sprite;
import code.projectiles.Projectile;

import java.util.List;

/**
 * Created by theo on 25-5-2016.
 */
public class BasicShield extends ShieldItem {
    private int range = 100;

    public BasicShield(EntityType type, Sprite sprite, int cooldown, int maxCapactity) {
        super(type, sprite, cooldown, maxCapactity);
    }

    public void update(){
        if (canUpdate()){
            List<Entity> projectiles = Engine.getEngine().getEntityStream().getEntities(EntityType.ENEMY_PROJECTILE);
            Entity player = Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
            for (Entity p: projectiles){
                if (canAbsorb()){
                    if(p.distanceTo(player) < range){
                        ((Projectile)p).setDead();
                        hit((Projectile)p);
                    }
                }
                else {
                    cd = cooldown;
                    break;
                }
            }
        }
    }
}
