package code.ui;

import code.components.sprite.SpriteComponent;
import code.graphics.MasterBuffer;

public class UIVisual extends UIElement {
	protected SpriteComponent sprite;

	public void render(MasterBuffer buffer){
		sprite.getSprite().renderSprite(position.getCoord(), sprite.getScale(), position.getRot(), 1, buffer);
		for(UIElement child: children){
			child.render(buffer);
		}
	}

	public SpriteComponent getSprite() {
		return sprite;
	}

	public void setSprite(SpriteComponent sprite) {
		this.sprite = sprite;
	}
}
