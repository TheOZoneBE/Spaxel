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
    private float factor;
    private AffectType affectType;

    public Component make(){
        switch (affectType){
            case SLOW:
                return new SlowAffectComponent(factor);
            case DISABLE_MOVE:
                return new DisableMoveAffectComponent();
            case DISABLE_SHOOT:
                return new DisableShootAffectComponent();
        }
        return null;
    }

    public float getFactor() {
        return factor;
    }

    public void setFactor(float factor) {
        this.factor = factor;
    }

    public AffectType getAffectType() {
        return affectType;
    }

    public void setAffectType(AffectType affectType) {
        this.affectType = affectType;
    }
}
