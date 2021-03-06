package code.components;

import code.entity.Entity;

public abstract class Behaviour extends Component {
    public Behaviour(ComponentType type) {
        super(type);
    }

    public abstract void execute(Entity parent);
}
