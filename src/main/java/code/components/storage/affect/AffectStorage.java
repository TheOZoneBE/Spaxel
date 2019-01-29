package code.components.storage.affect;

import code.components.ComponentType;
import code.components.Storage;

public class AffectStorage extends Storage {
    private double factor;

    public AffectStorage() {
        super(ComponentType.AFFECT_STORE);
    }

    public AffectStorage(double factor) {
        super(ComponentType.AFFECT_STORE);
        this.factor = factor;
    }

    /**
     * @return the factor
     */
    public double getFactor() {
        return factor;
    }

    /**
     * @param factor the factor to set
     */
    public void setFactor(double factor) {
        this.factor = factor;
    }

    public AffectStorage copy() {
        return new AffectStorage(factor);
    }
}
