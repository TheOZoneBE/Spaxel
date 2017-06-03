package code.components;

/**
 * Created by theo on 3/06/17.
 */
public class HealthComponent extends Component {
    private int health;
    private int maxHealth;

    public HealthComponent(int health, int maxHealth) {
        super(ComponentType.HEALTH);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
