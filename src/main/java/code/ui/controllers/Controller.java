package code.ui.controllers;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import code.graphics.MasterBuffer;
import code.ui.elements.UIType;
import code.ui.elements.UIElement;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ui", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = ClassSelectionController.class, name = "CLASS_SELECTION"),
        @JsonSubTypes.Type(value = CreditsController.class, name = "CREDITS"),
        @JsonSubTypes.Type(value = GameOverController.class, name = "GAME_OVER"),
        @JsonSubTypes.Type(value = MainController.class, name = "MAIN"),
        @JsonSubTypes.Type(value = PlayController.class, name = "PLAY"),
        @JsonSubTypes.Type(value = PauseController.class, name = "PAUSE"),
        @JsonSubTypes.Type(value = OptionsController.class, name = "OPTIONS"), })
public class Controller {
    UIElement root;
    private UIType ui;

    public Controller(UIType ui) {
        this.ui = ui;
    }

    public void initialize() {
        propagateController(root);
    }

    private void propagateController(UIElement current) {
        for (UIElement element : current.getChildren()) {
            propagateController(element);
        }
        current.setController(this);
    }

    public void update() {
        root.update();
    }

    public void render(MasterBuffer buffer) {
        root.render(buffer);
    }

    public UIElement getRoot() {
        return root;
    }

    public void setRoot(UIElement root) {
        this.root = root;
    }

    public UIType getUi() {
        return ui;
    }

    public void setUi(UIType ui) {
        this.ui = ui;
    }
}
