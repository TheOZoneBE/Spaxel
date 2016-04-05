package code.ui;

import code.graphics.RenderBuffer;
import code.graphics.Sprite;

public class UIBar extends UIElement{
	private int width;
	private double percent;
	
	public UIBar(int x, int y, int width, double rot, Sprite sprite) {
		super(x, y, rot, sprite);
		this.width = width;
		percent = 0;
	}
	
	public void setPercent(double percent){
		this.percent = percent;
	}
	
	public void render(RenderBuffer render){
		int renderWidth = (int)(width * percent);
		double dx = Math.round(Math.sin(rot));
		double dy = Math.round(Math.cos(rot));
		for (int i = 0; i < renderWidth; i++){
			sprite.render((int)(x + i*dx), (int)(y + i*dy), rot, render);
		}
	}

}
