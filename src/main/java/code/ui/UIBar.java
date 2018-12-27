package code.ui;

import code.Constants;
import code.graphics.MasterBuffer;
import code.graphics.RenderData;
import code.graphics.RenderLayer;
import code.math.VectorF;

public class UIBar extends UIVisual {
	private static final int TWO = 2;
	private int width;
	private float percent;

	public UIBar() {
		super();
	}

	public void render(MasterBuffer buffer) {
		float renderWidth = width * percent;
		VectorF offset = new VectorF(Math.round(Math.sin(position.getRot() * Constants.HALF_CIRLCE)),
				Math.round(Math.cos(position.getRot() * Constants.HALF_CIRLCE))).multiplicate(renderWidth / TWO);
		RenderData data = new RenderData();
		data.setPos(position.getCoord().sum(offset));

		data.setRot((float) Constants.HALF_CIRLCE * (position.getRot() - 1));
		data.setXScale(renderWidth);

		data.setYScale(sprite.getSprite().getHeight() * sprite.getScale());

		data.setColor(sprite.getSprite().getColor());

		data.setTexOffset(sprite.getSprite().getSpriteProperties());
		data.setSpriteSheetID(sprite.getSprite().getSpritesheetID());
		buffer.addNewSprite(RenderLayer.UI, data);
		for (UIElement child : children) {
			child.render(buffer);
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}
}
