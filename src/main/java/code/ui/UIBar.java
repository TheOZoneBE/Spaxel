package code.ui;

import code.Constants;
import code.graphics.MasterBuffer;
import code.graphics.RenderData;
import code.graphics.RenderLayer;
import code.math.VectorD;

public class UIBar extends UIVisual {
	private static final int TWO = 2;
	private int width;
	private double percent;

	public UIBar() {
		super();
	}

	@Override
	public void render(MasterBuffer buffer) {
		double renderWidth = width * percent;
		VectorD offset = new VectorD(Math.round(Math.sin(position.getRot() * Constants.HALF_CIRLCE)),
				Math.round(Math.cos(position.getRot() * Constants.HALF_CIRLCE))).multiplicate(renderWidth / TWO);
		RenderData data = new RenderData();
		data.setPos(position.getCoord().sum(offset));

		data.setRot(Constants.HALF_CIRLCE * (position.getRot() - 1));

		VectorD scale = sprite.getSprite().getDim().multiplicate(sprite.getScale());
		scale.setValue(0, renderWidth);
		data.setScale(scale);

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

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}
}
