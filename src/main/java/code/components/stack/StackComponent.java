package code.components.stack;

import code.components.Component;
import code.components.ComponentType;

/**
 * Created by theo on 16/06/17.
 */
public class StackComponent extends Component {
    private int stacks;

    public StackComponent(int stacks) {
        super(ComponentType.STACK);
        this.stacks = stacks;
    }

    public int getStacks() {
        return stacks;
    }

    public void setStacks(int stacks) {
        this.stacks = stacks;
    }
}
