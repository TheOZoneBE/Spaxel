package code.inventory;

import code.engine.Engine;
import code.engine.EntityType;
import code.entity.Entity;
import code.entity.Player;
import code.graphics.SpriteData;
import code.projectiles.Projectile;

import java.util.Iterator;

/**
 * Created by theo on 4-8-2016.
 */
public class ActiveShield extends ShieldItem{
    private int range = 100;

    public ActiveShield(EntityType type, String name, SpriteData sprite, SpriteData bar, int cooldown, SpriteData effectSprite, int maxCapactity) {
        super(type, name, sprite,bar, cooldown, effectSprite,maxCapactity );
    }

    public void update(){
        super.update();
        if (canUpdate()){
            Iterator<Entity> projectiles = Engine.getEngine().getEntityStream().getIterator(EntityType.PROJECTILE);
            Player player = (Player)Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
            while(projectiles.hasNext()){
                Entity p = projectiles.next();
                if (canAbsorb()){
                    if(p.distanceTo(player) < range){
                        Projectile ins = (Projectile)p;
                        hit(ins);
                        reduceCooldown(player.getItemIterator(EntityType.MOUSE1ITEM));
                        reduceCooldown(player.getItemIterator(EntityType.MOUSE3ITEM));
                        reduceCooldown(player.getItemIterator(EntityType.SHIPITEM));
                        ins.setDead();
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

    public void reduceCooldown(Iterator<Item> items){
        while (items.hasNext()){
            Item i = items.next();
            i.setCD(i.getCD() == 0 ? 0 : i.getCD()-1);
        }
    }

    public Item copy(){
        return new ActiveShield(type,name, sprite, bar, cooldown, effectSprite, maxCapactity);
    }
}
