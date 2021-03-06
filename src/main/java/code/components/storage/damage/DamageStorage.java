package code.components.storage.damage;

import code.components.Storage;
import code.components.ComponentType;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the ability to get damaged
 * 
 * Created by theo on 8/06/17.
 */
public class DamageStorage extends Storage {
    private List<Damage> damages;

    /**
     * Create a new DamageComponent
     */
    public DamageStorage() {
        super(ComponentType.DAMAGE);
        damages = new ArrayList<>();
    }

    /**
     * Add damage to this component
     * 
     * @param damage the damage to add
     */
    public synchronized void addDamage(Damage damage) {
        damages.add(damage);
    }

    public List<Damage> getDamages() {
        return damages;
    }

    /**
     * remove Damage from this component
     * 
     * @param damage the damage to remove
     */
    public synchronized void removeDamage(Damage damage) {
        damages.remove(damage);
    }

    public DamageStorage copy() {
        return new DamageStorage();
    }
}
