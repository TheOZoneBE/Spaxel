package code.components.age;

import code.components.Component;
import code.components.ComponentType;

/**
 * Created by theo on 3/06/17.
 */
public class AgeComponent extends Component {
    private int life;
    private int maxLife;

    public AgeComponent(int life, int maxLife) {
        super(ComponentType.AGE);
        this.life = life;
        this.maxLife = maxLife;
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

    public Component copy() {
        return new AgeComponent(life, maxLife);
    }
}
