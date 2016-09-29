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
public class AntiShield extends ShieldItem{
    private int range = 100;
    private float healingPerc = .25f;

    public AntiShield(EntityType type, String name, SpriteData sprite, SpriteData bar, int cooldown, SpriteData effectSprite, int maxCapactity) {
        super(type, name, sprite,bar, cooldown, effectSprite,maxCapactity );
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
                        Projectile ins = (Projectile)p;
                        hit(ins);
                        Player pl = (Player)Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
                        if(pl.getHealth() < pl.getMaxHealth()){
                            float total = pl.getHealth() + healingPerc*ins.getDamage();
                            pl.setHealth(total < pl.getMaxHealth() ? (int)total : pl.getMaxHealth());
                        }
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

    public Item copy(){
        return new AntiShield(type,name, sprite, bar, cooldown, effectSprite, maxCapactity);
    }
}
