package code.factories.components;

import code.components.Component;
import code.components.cooldown.CooldownComponent;

/**
 * Created by theo on 19/06/17.
 */
public class CooldownComponentFactory extends ComponentFactory {
    private int cd;
    private int cdAmount;

    public Component make(){
        return new CooldownComponent(cd, cdAmount);
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
}
