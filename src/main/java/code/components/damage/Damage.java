package code.components.damage;

/**
 * Created by theo on 8/06/17.
 */
public class Damage {
    private int damageValue;

    public Damage(int damageValue) {
        this.damageValue = damageValue;
    }

    public int getDamage() {
        return damageValue;
    }

    public void setDamage(int damageValue) {
        this.damageValue = damageValue;
    }
}
