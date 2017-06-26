package code.components.health;

import code.components.Component;
import code.components.ComponentType;

/**
 * Created by theo on 3/06/17.
 */
public class HealthComponent extends Component {
    private int health;
    private int maxHealth;

    public HealthComponent(int health, int maxHealth) {
        super(ComponentType.HEALTH);
        this.health = health;
        this.maxHealth = maxHealth;
    }

    public void levelUp(int level){
        maxHealth = 25 * level * level + 25 * level + 50;
        health = maxHealth;
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
