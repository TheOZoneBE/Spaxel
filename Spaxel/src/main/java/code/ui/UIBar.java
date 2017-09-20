package code.ui;

import code.graphics.MasterBuffer;
import code.graphics.RenderData;
import code.graphics.RenderLayer;
import code.math.VectorF;

public class UIBar extends UIVisual{
	private int width;
	private float percent;
	
	public void render(MasterBuffer buffer){
		float renderWidth = width * percent;
		VectorF offset = new VectorF(Math.round(Math.sin(position.getRot()*Math.PI/2)), Math.round(Math.cos(position.getRot()*Math.PI/2))).multiplicate(renderWidth/2);
		RenderData data = new RenderData();
		data.setPos(position.getCoord().sum(offset));

		data.setRot((float)Math.PI/2*(position.getRot()-1));
		data.setXScale(renderWidth);

		data.setYScale(sprite.getSprite().getHeight()*sprite.getScale());

		data.setColor(sprite.getSprite().getColor() == 0 ? 0 : sprite.getSprite().getColor());

		data.setTexOffset(sprite.getSprite().getSpriteProperties());
		data.setSpriteSheetID(sprite.getSprite().getSpritesheetID());
		buffer.addNewSprite(RenderLayer.UI, data);
		for(UIElement child: children){
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
