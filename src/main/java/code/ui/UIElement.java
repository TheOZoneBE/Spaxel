package code.ui;

import code.components.position.PositionComponent;
import code.graphics.MasterBuffer;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "class")
@JsonSubTypes({ @JsonSubTypes.Type(value = UIElement.class, name = "UIElement"),
		@JsonSubTypes.Type(value = UILabel.class, name = "UILabel"),
		@JsonSubTypes.Type(value = UIButton.class, name = "UIButton"),
		@JsonSubTypes.Type(value = UIVisual.class, name = "UIVisual"),
		@JsonSubTypes.Type(value = UIBar.class, name = "UIBar"),

})
public class UIElement {
	protected String id;
	protected PositionComponent position;
	protected Controller controller;
	protected List<UIElement> children;

	public UIElement() {
		children = new ArrayList<>();
	}

	public void update() {
		for (UIElement child : children) {
			child.update();
		}
	}

	public void render(MasterBuffer buffer) {
		for (UIElement child : children) {
			child.render(buffer);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PositionComponent getPosition() {
		return position;
	}

	public void setPosition(PositionComponent position) {
		this.position = position;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public List<UIElement> getChildren() {
		return children;
	}

	public void setChildren(List<UIElement> children) {
		this.children = children;
	}

	public UIElement findById(String id) {
		if (id.equals(this.id)) {
			return this;
		} else {
			for (UIElement element : children) {
				UIElement found = element.findById(id);
				if (found != null) {
					return found;
				}
			}
		}
		return null;
	}
}
