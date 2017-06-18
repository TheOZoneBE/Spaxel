package code.components.hit;

import code.engine.NEntity;

/**
 * Created by theo on 18/06/17.
 */
public class BasicLaserHitComponent extends HitComponent {
    public BasicLaserHitComponent(int damage) {
        super(HitType.BASIC_LASER, damage);
    }

    public void hit(NEntity entity){

    }
}
