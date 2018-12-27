package code.components.damage;

import code.components.Component;
import code.components.ComponentType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theo on 8/06/17.
 */
public class DamageComponent extends Component {
    private List<Damage> damages;

    public DamageComponent() {
        super(ComponentType.DAMAGE);
        damages = new ArrayList<>();
    }

    public synchronized void addDamage(Damage damage) {
        damages.add(damage);
    }

    public List<Damage> getDamages() {
        return damages;
    }

    public synchronized void removeDamage(Damage damage) {
        damages.remove(damage);
    }

    public Component copy() {
        return new DamageComponent();
    }
}
