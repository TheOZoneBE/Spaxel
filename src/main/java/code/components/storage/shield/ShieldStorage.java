package code.components.storage.shield;

import code.components.ComponentType;
import code.components.Storage;

public class ShieldStorage extends Storage {
    private int currentCapacity;
    private int maxCapacity;

    public ShieldStorage() {
        super(ComponentType.SHIELD);
    }

    public ShieldStorage(int currentCapacity, int maxCapacity) {
        super(ComponentType.SHIELD);
        this.currentCapacity = currentCapacity;
        this.maxCapacity = maxCapacity;
    }

    /**
     * @return the currentCapacity
     */
    public int getCurrentCapacity() {
        return currentCapacity;
    }

    /**
     * @param currentCapacity the currentCapacity to set
     */
    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    /**
     * @return the maxCapacity
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * @param maxCapacity the maxCapacity to set
     */
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void subCapacity(int subtraction) {
        currentCapacity -= subtraction;
    }

    public void resetCapacity() {
        currentCapacity = maxCapacity;
    }

    public ShieldStorage copy() {
        return new ShieldStorage(currentCapacity, maxCapacity);
    }
}
