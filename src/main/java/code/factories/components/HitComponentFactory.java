package code.factories.components;

import code.components.Component;
import code.components.hit.BasicLaserHitComponent;
import code.components.hit.BasicMissileHitComponent;
import code.components.hit.PiercingLaserHitComponent;
import code.components.hit.DisruptLaserHitComponent;
import code.components.hit.SlowingLaserHitComponent;
import code.components.hit.ClusterMissileHitComponent;
import code.components.hit.HackingMissileHitComponent;
import code.components.hit.HomingMissileHitComponent;
import code.components.hit.HitType;

/**
 * Created by theo on 18/06/17.
 */
public class HitComponentFactory extends ComponentFactory {
    private int damage;
    private HitType hitType;

    public HitComponentFactory() {
        super();
    }

    @Override
    public Component make() {
        Component result = null;
        switch (hitType) {
        case BASIC_LASER:
            result = new BasicLaserHitComponent(damage);
            break;
        case PIERCING_LASER:
            result = new PiercingLaserHitComponent(damage);
            break;
        case DISRUPT_LASER:
            result = new DisruptLaserHitComponent(damage);
            break;
        case SLOWING_LASER:
            result = new SlowingLaserHitComponent(damage);
            break;
        case BASIC_MISSILE:
            result = new BasicMissileHitComponent(damage);
            break;
        case CLUSTER_MISSILE:
            result = new ClusterMissileHitComponent(damage);
            break;
        case HACKING_MISSILE:
            result = new HackingMissileHitComponent(damage);
            break;
        case HOMING_MISSILE:
            result = new HomingMissileHitComponent(damage);
            break;
        default:
            break;
        }
        return result;
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
