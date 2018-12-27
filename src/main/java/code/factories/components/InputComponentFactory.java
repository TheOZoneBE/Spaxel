package code.factories.components;

import code.components.Component;
import code.components.input.InputType;
import code.components.input.PlayerInputComponent;

/**
 * Created by theo on 21/06/17.
 */
public class InputComponentFactory extends ComponentFactory {
    private InputType inputType;

    public InputComponentFactory() {
        super();
    }

    public Component make() {
        if (inputType == InputType.PLAYER) {
            return new PlayerInputComponent();
        } else {
            return null;
        }
    }

    public InputType getInputType() {
        return inputType;
    }

    public void setInputType(InputType inputType) {
        this.inputType = inputType;
    }
}
