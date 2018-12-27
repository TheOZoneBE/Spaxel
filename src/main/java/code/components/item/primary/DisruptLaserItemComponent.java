package code.components.item.primary;

import code.components.item.ItemType;
import code.components.item.ShootItemComponent;
import code.components.Component;

/**
 * Created by theod on 28-6-2017.
 */
public class DisruptLaserItemComponent extends ShootItemComponent {
    public DisruptLaserItemComponent(){
        super(ItemType.PRIMARY, "disrupt_laser", "disrupt_laser_projectile_industry");
    }

    public Component copy(){
        return new DisruptLaserItemComponent();
    }
}
