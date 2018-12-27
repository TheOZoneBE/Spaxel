package code.factories.components;

import code.components.Component;
import code.components.health.HealthComponent;

/**
 * Created by theo on 3/06/17.
 */
public class HealthComponentFactory extends ComponentFactory {
    private int health;
    private int baseHealth;

    public HealthComponentFactory() {
        super();
    }

    public Component make() {
        return new HealthComponent(health, baseHealth);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }
}
