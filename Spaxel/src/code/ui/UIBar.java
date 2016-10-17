package code.ui;

import code.graphics.MasterBuffer;
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
	
	public void render(MasterBuffer render){
		int renderWidth = (int)(width * percent);
		float dx = Math.round(Math.sin(rot));
		float dy = Math.round(Math.cos(rot));
		for (int i = 0; i < renderWidth; i++){
			sprite.renderSprite((int)(x + i*dx), (int)(y + i*dy),2, rot - (float)Math.PI/2,1,false, render);
		}
	}

	public void render(int xPos, int yPos, MasterBuffer render){
		int renderWidth = (int)(width * percent);
		float dx = Math.round(Math.sin(rot));
		float dy = Math.round(Math.cos(rot));
		for (int i = 0; i < renderWidth; i++){
			sprite.renderSprite((int)(xPos + i*dx), (int)(yPos + i*dy),2, rot - (float)Math.PI/2,1, false, render);
		}
	}

}
