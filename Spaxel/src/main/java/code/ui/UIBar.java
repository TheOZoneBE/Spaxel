package code.ui;

import code.graphics.MasterBuffer;
import code.math.VectorF;

public class UIBar extends UIVisual{
	private int width;
	private float percent;
	
	public void render(MasterBuffer buffer){
		int renderWidth = (int)(width * percent);
		VectorF dxy = new VectorF(Math.round(Math.sin(position.getRot()*Math.PI/2)), Math.round(Math.cos(position.getRot()*Math.PI/2)));
		for (int i = 0; i < renderWidth; i++){
			sprite.getSprite().renderSprite(position.getCoord().sum(dxy.multiplicate(i)), sprite.getScale(), (float)Math.PI/2*(position.getRot() -1), 1, buffer);
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
