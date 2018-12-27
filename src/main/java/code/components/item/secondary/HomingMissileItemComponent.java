package code.components.item.secondary;

import code.components.item.ItemType;
import code.components.item.ShootItemComponent;
import code.components.Component;

/**
 * Created by theod on 2-7-2017.
 */
public class HomingMissileItemComponent extends ShootItemComponent {
    public HomingMissileItemComponent(){
        super(ItemType.SECONDARY, "homing_missile", "homing_missile_projectile_industry");
    }
    public Component copy(){
        return new HomingMissileItemComponent();
    }
}
