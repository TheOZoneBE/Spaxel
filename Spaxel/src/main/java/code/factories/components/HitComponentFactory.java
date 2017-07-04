package code.factories.components;

import code.components.Component;
import code.components.hit.*;
import code.components.item.primary.PiercingLaserItemComponent;
import code.components.item.primary.SlowingLaserItemComponent;
import code.components.item.secondary.ClusterMissileItemComponent;

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
            case PIERCING_LASER:
                return new PiercingLaserHitComponent(damage);
            case DISRUPT_LASER:
                return new DisruptLaserHitComponent(damage);
            case SLOWING_LASER:
                return new SlowingLaserHitComponent(damage);
            case BASIC_MISSILE:
                return new BasicLaserHitComponent(damage);
            case CLUSTER_MISSILE:
                return new ClusterMissileHitComponent(damage);
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
