package code.inventory;

import code.engine.Engine;
import code.engine.EntityType;
import code.entity.Entity;
import code.graphics.Sprite;
import code.projectiles.Projectile;

import java.util.Iterator;

/**
 * Created by theo on 4-8-2016.
 */
public class ForceShield extends ShieldItem {
    private int range = 100;

    public ForceShield(EntityType type, String name, Sprite sprite, Sprite bar, int cooldown, Sprite effectSprite, int maxCapactity) {
        super(type, name, sprite, bar, cooldown, effectSprite, maxCapactity);
    }

    public void update(){
        super.update();
        if (canUpdate()){
            Iterator<Entity> projectiles = Engine.getEngine().getEntityStream().getIterator(EntityType.ENEMY_PROJECTILE);
            Entity player = Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
            while(projectiles.hasNext()){
                Entity p = projectiles.next();
                if (canAbsorb()){
                    if(p.distanceTo(player) < range){
                        Projectile ins = (Projectile) p;
                        hit(ins);
                        ins.setRot((float)(ins.getRot()+Math.PI));

                    }
                }
                else {
                    cd = cooldown;
                    currentCap = maxCapactity;
                    break;
                }
            }
        }
    }

    public Item copy(){
        return new ForceShield(type,name, sprite, bar, cooldown, effectSprite, maxCapactity);
    }
}
