package code.components.health;

import code.components.Component;
import code.components.ComponentType;

/**
 * Created by theo on 3/06/17.
 */
public class HealthComponent extends Component {
    private int currentHealth;
    private int baseHealth;
    private int maxHealth;

    public HealthComponent(int health, int baseHealth) {
        super(ComponentType.HEALTH);
        this.currentHealth = health;
        this.maxHealth = baseHealth;
        this.baseHealth = baseHealth;
    }

    public void levelUp(int level) {
        maxHealth = baseHealth * level;
        currentHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int health) {
        this.currentHealth = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public Component copy() {
        return new HealthComponent(currentHealth, baseHealth);
    }
}
