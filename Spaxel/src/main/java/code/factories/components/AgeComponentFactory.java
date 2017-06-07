package code.factories.components;

import code.components.AgeComponent;
import code.components.Component;

/**
 * Created by theo on 3/06/17.
 */
public class AgeComponentFactory extends ComponentFactory {
    private int life;
    private int maxLife;

    public AgeComponentFactory(){

    }

    public Component make(){
        return new AgeComponent(life, maxLife);
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }
}