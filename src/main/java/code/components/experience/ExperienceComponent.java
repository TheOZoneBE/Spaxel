package code.components.experience;

import code.components.Component;
import code.components.ComponentType;

/**
 * Created by theo on 26/06/17.
 */
public class ExperienceComponent extends Component {
    private static final int XP_FUNC_A = 25;
    private static final int XP_FUNC_B = 25;
    private static final int XP_FUNC_C = 50;

    private int xp;
    private int level;

    public ExperienceComponent(int xp, int level) {
        super(ComponentType.EXPERIENCE);
        this.xp = xp;
        this.level = level;
    }

    public int getXpToLevel() {
        return level * level * XP_FUNC_A + level * XP_FUNC_B + XP_FUNC_C;
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
