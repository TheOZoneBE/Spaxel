package code.components.item.secondary;

import code.components.item.ItemType;
import code.components.item.ShootItemComponent;

/**
 * Created by theod on 2-7-2017.
 */
public class BasicMissileItemComponent extends ShootItemComponent {
    public BasicMissileItemComponent() {
        super(ItemType.SECONDARY, "basic_missile", "basic_missile_projectile_industry");
    }
}
