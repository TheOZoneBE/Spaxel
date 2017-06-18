package code.components.hit;

import code.components.Component;
import code.components.ComponentType;
import code.engine.NEntity;

/**
 * Created by theo on 18/06/17.
 */
public class HitComponent extends Component{
    private HitType hitType;
    protected int damage;

    public HitComponent(HitType hitType, int damage){
        super(ComponentType.HIT);
        this.hitType = hitType;
        this.damage = damage;
    }

    public void hit(NEntity entity){

    }

    public HitType getHitType() {
        return hitType;
    }

    public void setHitType(HitType hitType) {
        this.hitType = hitType;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
