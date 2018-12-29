package code.factories.components;

import code.components.Component;
import code.components.affect.AffectType;
import code.components.affect.DisableMoveAffectComponent;
import code.components.affect.DisableShootAffectComponent;
import code.components.affect.SlowAffectComponent;

/**
 * Created by theod on 28-6-2017.
 */
public class AffectComponentFactory extends ComponentFactory {
    private double factor;
    private AffectType affectType;

    public AffectComponentFactory() {
        super();
    }

    @Override
    public Component make() {
        Component result = null;
        switch (affectType) {
        case SLOW:
            result = new SlowAffectComponent(factor);
            break;
        case DISABLE_MOVE:
            result = new DisableMoveAffectComponent();
            break;
        case DISABLE_SHOOT:
            result = new DisableShootAffectComponent();
            break;
        default:
            break;
        }
        return result;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public AffectType getAffectType() {
        return affectType;
    }

    public void setAffectType(AffectType affectType) {
        this.affectType = affectType;
    }
}
