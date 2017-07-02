package code.factories.components;

import code.components.Component;
import code.components.item.ItemType;
import code.components.item.primary.BasicLaserItemComponent;
import code.components.item.primary.DisruptLaserItemComponent;
import code.components.item.primary.PiercingLaserItemComponent;
import code.components.item.primary.SlowingLaserItemComponent;
import code.components.item.secondary.BasicMissileItemComponent;
import code.components.item.secondary.ClusterMissileItemComponent;
import code.components.item.secondary.HackingMissileItemComponent;
import code.components.item.secondary.HomingMissileItemComponent;

/**
 * Created by theo on 19/06/17.
 */
public class ItemComponentFactory extends ComponentFactory {
    private String name;

    public Component make(){
        switch(name){
            case "basic_laser":
                return new BasicLaserItemComponent();
            case "piercing_laser":
                return new PiercingLaserItemComponent();
            case "disrupt_laser":
                return new DisruptLaserItemComponent();
            case "slowing_laser":
                return new SlowingLaserItemComponent();
            case "basic_missile":
                return new BasicMissileItemComponent();
            case "hacking_missile":
                return new HackingMissileItemComponent();
            case "homing_missile":
                return new HomingMissileItemComponent();
            case "cluster_missile":
                return new ClusterMissileItemComponent();
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
