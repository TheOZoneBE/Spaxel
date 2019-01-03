package code.factories.components;

import code.components.Component;
import code.components.item.primary.BasicLaserItemComponent;
import code.components.item.primary.DisruptLaserItemComponent;
import code.components.item.primary.PiercingLaserItemComponent;
import code.components.item.primary.SlowingLaserItemComponent;
import code.components.item.secondary.BasicMissileItemComponent;
import code.components.item.secondary.ClusterMissileItemComponent;
import code.components.item.secondary.HackingMissileItemComponent;
import code.components.item.secondary.HomingMissileItemComponent;
import code.components.item.ship.ActiveShieldItemComponent;
import code.components.item.ship.AntiShieldItemComponent;
import code.components.item.ship.BasicShieldItemComponent;
import code.components.item.ship.ForceShieldItemComponent;
import code.factories.entities.EntityIndustry;

/**
 * The ItemComponentFactory is responsible for creating new ItemComponents
 * 
 * Created by theo on 19/06/17.
 */
public class ItemComponentFactory extends ComponentFactory {
    private String name;
    private int capacity;
    private int maxCapacity;
    private EntityIndustry effectIndustry;

    /**
     * Creates a new ItemComponentFactory
     */
    public ItemComponentFactory() {
        super();
    }

    @Override
    public Component make() {
        switch (name) {
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
            case "basic_shield":
                return new BasicShieldItemComponent(capacity, maxCapacity,
                        effectIndustry.produce());
            case "anti_shield":
                return new AntiShieldItemComponent(capacity, maxCapacity, effectIndustry.produce());
            case "active_shield":
                return new ActiveShieldItemComponent(capacity, maxCapacity,
                        effectIndustry.produce());
            case "force_shield":
                return new ForceShieldItemComponent(capacity, maxCapacity,
                        effectIndustry.produce());
            default:
                break;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public EntityIndustry getEffectIndustry() {
        return effectIndustry;
    }

    public void setEffectIndustry(EntityIndustry effectIndustry) {
        this.effectIndustry = effectIndustry;
    }
}
