package code.inventory;

import code.engine.EntityType;
import code.graphics.RenderBuffer;
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

    public ShieldItem(EntityType type, Sprite sprite, Sprite bar, int cooldown, int maxCapactity){
        super(type, sprite, bar);
        this.cooldown = cooldown;
        this.maxCapactity = maxCapactity;
        currentCap = maxCapactity;
        cd = 0;
    }

    public void update(){
        reduceCD();
        cooldownBar.setPercent((double)cd/(double)cooldown);
    }

    public void reduceCD(){
        if (cd != 0){
            cd--;
        }
    }

    public boolean canUpdate(){
        return cd <= 0;
    }

    public void hit(Projectile p){
        currentCap -= p.getDamage();
    }

    public boolean canAbsorb(){
        return currentCap >= 0;
    }

    public void render(int xPos, int yPos, RenderBuffer render){
        sprite.render(xPos, yPos, render);
        cooldownBar.render(xPos - 24, yPos,render);
    }
}
