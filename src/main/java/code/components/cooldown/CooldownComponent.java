package code.components.cooldown;

import code.components.Component;
import code.components.ComponentType;

/**
 * Represents a cooldown for an entity
 * 
 * Created by theo on 16/06/17.
 */
public class CooldownComponent extends Component {
    private int cd;
    private int cdAmount;

    /**
     * Create a new CooldownComponent with the specified cooldown
     * 
     * @param cd       the current cooldown
     * @param cdAmount the maximum cooldown
     */
    public CooldownComponent(int cd, int cdAmount) {
        super(ComponentType.COOLDOWN);
        this.cd = cd;
        this.cdAmount = cdAmount;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public int getCdAmount() {
        return cdAmount;
    }

    public void setCdAmount(int cdAmount) {
        this.cdAmount = cdAmount;
    }

    public Component copy() {
        return new CooldownComponent(cd, cdAmount);
    }
}
