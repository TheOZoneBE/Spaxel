package code.inventory;

import code.engine.Engine;
import code.engine.EntityType;
import code.entity.Entity;
import code.graphics.RenderBuffer;
import code.graphics.Sprite;
import code.projectiles.Projectile;

/**
 * Created by theo on 25-5-2016.
 */
public class ShieldItem extends EffectItem {
    protected int maxCapactity;
    protected int currentCap;

    public ShieldItem(EntityType type,String name,  Sprite sprite, Sprite bar, int cooldown, Sprite effectSprite, int maxCapactity){
        super(type, name, sprite, bar, cooldown, effectSprite);
        this.maxCapactity = maxCapactity;
        currentCap = maxCapactity;
    }

    public void update(){
        reduceCD();
        cooldownBar.setPercent((float)cd/(float)cooldown);
    }



    public void hit(Projectile p){
        currentCap -= p.getDamage();
    }

    public boolean canAbsorb(){
        return currentCap >= 0;
    }
}
