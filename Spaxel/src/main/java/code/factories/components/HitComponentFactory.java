package code.factories.components;

import code.components.Component;
import code.components.hit.BasicLaserHitComponent;
import code.components.hit.HitType;

/**
 * Created by theo on 18/06/17.
 */
public class HitComponentFactory extends ComponentFactory {
    private int damage;
    private HitType hitType;

    public Component make(){
        switch (hitType) {
            case BASIC_LASER:
                return new BasicLaserHitComponent(damage);
        }
        return null;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public HitType getHitType() {
        return hitType;
    }

    public void setHitType(HitType hitType) {
        this.hitType = hitType;
    }
}
