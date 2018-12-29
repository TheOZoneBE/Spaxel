package code.factories.components;

import code.components.Component;
import code.components.stack.StackComponent;

/**
 * Created by theo on 19/06/17.
 */
public class StackComponentFactory extends ComponentFactory {
    private int stacks;

    public StackComponentFactory() {
        super();
    }

    @Override
    public Component make() {
        return new StackComponent(stacks);
    }

    public int getStacks() {
        return stacks;
    }

    public void setStacks(int stacks) {
        this.stacks = stacks;
    }
}
