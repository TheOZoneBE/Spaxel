package code.components.item.primary;

import code.components.item.ItemType;
import code.components.item.ShootItemComponent;
import code.components.Component;

/**
 * Created by theod on 28-6-2017.
 */
public class SlowingLaserItemComponent extends ShootItemComponent {
    public SlowingLaserItemComponent(){
        super(ItemType.PRIMARY, "slowing_laser", "slowing_laser_projectile_industry");
    }
    public Component copy(){
        return new SlowingLaserItemComponent();
    }
}
