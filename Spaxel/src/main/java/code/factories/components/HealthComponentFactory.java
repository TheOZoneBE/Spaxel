package code.factories.components;

import code.components.Component;
import code.components.HealthComponent;

/**
 * Created by theo on 3/06/17.
 */
public class HealthComponentFactory extends ComponentFactory{
    private int health;
    private int maxHealth;

    public HealthComponentFactory(){

    }

    public Component make(){
        return new HealthComponent(health, maxHealth);
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
