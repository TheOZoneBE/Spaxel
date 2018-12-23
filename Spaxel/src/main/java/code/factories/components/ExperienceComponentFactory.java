package code.factories.components;

import code.components.Component;
import code.components.experience.ExperienceComponent;

/**
 * Created by theo on 26/06/17.
 */
public class ExperienceComponentFactory extends ComponentFactory {
    private int xp;
    private int level;

    public Component make(){
        return new ExperienceComponent(xp, level);
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
