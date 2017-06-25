package code.ui;

import code.graphics.MasterBuffer;
import code.math.VectorF;

public class UIBar extends UIVisual{
	private int width;
	private float percent;
	
	public void render(MasterBuffer buffer){
		int renderWidth = (int)(width * percent);
		VectorF dxy = new VectorF(Math.round(Math.sin(position.getRot())), Math.round(Math.cos(position.getRot())));
		for (int i = 0; i < renderWidth; i++){
			sprite.getSprite().renderSprite(position.getCoord().sum(dxy.multiplicate(i)), sprite.getScale(), position.getRot() - (float)Math.PI/2, 1, buffer);
		}
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
