package code.ui.elements;

import code.components.sprite.SpriteComponent;
import code.graphics.MasterBuffer;
import code.graphics.RenderData;
import code.graphics.RenderLayer;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "uivisual")
public class UIVisual extends UIElement {
	protected SpriteComponent sprite;

	@Override
	public void render(MasterBuffer buffer) {
		RenderData data = new RenderData();
		data.setPos(position.getCoord());
		data.setRot(position.getRot());
		data.setScale(sprite.getSprite().getDim().multiplicate(sprite.getScale()));
		data.setSpriteSheetID(sprite.getSprite().getSpritesheetID());
		data.setTexOffset(sprite.getSprite().getSpriteProperties());
		buffer.addNewSprite(RenderLayer.UI, data);

		for (UIElement child : children) {
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
