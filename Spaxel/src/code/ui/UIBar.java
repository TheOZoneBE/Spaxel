package code.ui;

import code.graphics.RenderBuffer;
import code.graphics.Sprite;

import java.awt.*;

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
	
	public void render(Graphics g, RenderBuffer render){
		int renderWidth = (int)(width * percent);
		double dx = Math.round(Math.sin(rot));
		double dy = Math.round(Math.cos(rot));
		for (int i = 0; i < renderWidth; i++){
			sprite.render((int)(x + i*dx), (int)(y + i*dy), rot - Math.PI/2, render);
		}
	}

	public void render(int xPos, int yPos, RenderBuffer render){
		int renderWidth = (int)(width * percent);
		double dx = Math.round(Math.sin(rot));
		double dy = Math.round(Math.cos(rot));
		for (int i = 0; i < renderWidth; i++){
			sprite.render((int)(xPos + i*dx), (int)(yPos + i*dy), rot - Math.PI/2, render);
		}
	}

}
