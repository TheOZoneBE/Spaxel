package code.components.input;

import code.components.Component;
import code.components.ComponentType;
import code.engine.NEntity;

/**
 * Created by theo on 21/06/17.
 */
public class InputComponent extends Component {
    private InputType inputType;

    public InputComponent(InputType inputType) {
        super(ComponentType.INPUT);
        this.inputType = inputType;
    }

    public void update(NEntity entity){

    }
}
