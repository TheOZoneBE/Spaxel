package code.ui;

import code.graphics.RenderBuffer;
import code.graphics.SpriteData;

import java.awt.*;

public class UIBar extends UIElement{
	private int width;
	private float percent;
	
	public UIBar(int x, int y, int width, float rot, SpriteData sprite) {
		super(x, y, rot, sprite);
		this.width = width;
		percent = 0;
	}
	
	public void setPercent(float percent){
		this.percent = percent;
	}
	
	public void render(Graphics g, RenderBuffer render){
		int renderWidth = (int)(width * percent);
		float dx = Math.round(Math.sin(rot));
		float dy = Math.round(Math.cos(rot));
		for (int i = 0; i < renderWidth; i++){
			//TODO sprite.renderSprite((int)(x + i*dx), (int)(y + i*dy), rot - (float)Math.PI/2, render);
		}
	}

	public void render(int xPos, int yPos, RenderBuffer render){
		int renderWidth = (int)(width * percent);
		float dx = Math.round(Math.sin(rot));
		float dy = Math.round(Math.cos(rot));
		for (int i = 0; i < renderWidth; i++){
			//TODO sprite.render((int)(xPos + i*dx), (int)(yPos + i*dy), rot - (float)Math.PI/2, render);
		}
	}

}
