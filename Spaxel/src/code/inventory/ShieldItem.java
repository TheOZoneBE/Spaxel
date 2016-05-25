package code.inventory;

import code.engine.EntityType;
import code.graphics.Sprite;
import code.projectiles.Projectile;

/**
 * Created by theo on 25-5-2016.
 */
public class ShieldItem extends Item{
    protected int cooldown;
    protected int maxCapactity;
    protected int currentCap;
    protected int cd;

    public ShieldItem(EntityType type, Sprite sprite, int cooldown, int maxCapactity){
        super(type, sprite);
        this.cooldown = cooldown;
        this.maxCapactity = maxCapactity;
        currentCap = maxCapactity;
        cd = 0;
    }

    public void update(){
    }

    public boolean canUpdate(){
        cd--;
        return cd != 0;
    }

    public void hit(Projectile p){
        currentCap += p.getDamage();
    }

    public boolean canAbsorb(){
        return currentCap < maxCapactity;
    }
}
