package code.components.experience;

import code.components.Component;
import code.components.ComponentType;

/**
 * Created by theo on 26/06/17.
 */
public class ExperienceComponent extends Component {
    private int xp;
    private int level;

    public ExperienceComponent(int xp, int level) {
        super(ComponentType.EXPERIENCE);
        this.xp = xp;
        this.level = level;
    }

    public int getXpToLevel(){
        return level*level*25 + level*25 + 50;
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
