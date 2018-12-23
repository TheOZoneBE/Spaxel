package code.components.item.primary;

import code.components.item.ItemType;
import code.components.item.ShootItemComponent;


/**
 * Created by theo on 16/06/17.
 */
public class BasicLaserItemComponent extends ShootItemComponent {
    public BasicLaserItemComponent() {
        super(ItemType.PRIMARY, "basic_laser", "basic_laser_projectile_industry");
    }
}
