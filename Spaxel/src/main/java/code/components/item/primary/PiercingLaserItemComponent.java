package code.components.item.primary;

import code.components.item.ItemType;
import code.components.item.ShootItemComponent;

/**
 * Created by theod on 28-6-2017.
 */
public class PiercingLaserItemComponent extends ShootItemComponent {
    public PiercingLaserItemComponent() {
        super(ItemType.PRIMARY, "piercing_laser", "piercing_laser_projectile_industry");
    }
}
