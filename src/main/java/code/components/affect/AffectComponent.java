package code.components.affect;

import code.components.Component;
import code.components.ComponentType;
import code.engine.NEntity;

/**
 * Created by theod on 28-6-2017.
 */
public class AffectComponent extends Component {
    private AffectType affectType;
    protected double factor;

    public AffectComponent(AffectType affectType, double factor) {
        super(ComponentType.AFFECT);
        this.affectType = affectType;
        this.factor = factor;
    }

    public void affect(NEntity entity, NEntity victim) {

    }

    public AffectType getAffectType() {
        return affectType;
    }

    public void setAffectType(AffectType affectType) {
        this.affectType = affectType;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }
}
