package code.components.item.secondary;

import code.components.item.ItemType;
import code.components.item.ShootItemComponent;

/**
 * Created by theod on 2-7-2017.
 */
public class HackingMissileItemComponent extends ShootItemComponent {
    public HackingMissileItemComponent(){
        super(ItemType.SECONDARY, "hacking_missile", "hacking_missile_projectile_industry");
    }
}
