package code.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import code.engine.Engine;
import code.entity.Entity;
import code.graphics.MasterBuffer;
import code.graphics.RenderBuffer;
import code.graphics.SpriteData;
import code.ui.UIElement;

public class Label extends UIElement{
	private String text;
	private int scale;
	
	public Label(int x, int y, String text, int scale){
		super(x, y, null);
		this.scale = scale;
		this.text = text;
	}

	public int calculateOffset(int i){
		int index = text.indexOf('\\', i);
		if (index == -1) {
			index = text.length();
		}
		return (1-index + i) * 6 * scale;
	}
	
	public void render(MasterBuffer render){
		int xOffset = calculateOffset(0);
		int yOffset = 0;
		for (int i = 0; i < text.length(); i++){
			String c = text.substring(i, i+1).toLowerCase();
			if (!c.equals(" ")){
				SpriteData cSprite = Engine.getEngine().getSpriteAtlas().get(c);
				if (cSprite != null){
					cSprite.renderSprite((int)(x + xOffset), (int)(y+yOffset), scale, 0, 1, false, render);
				}
			}
			if (c.equals("\\")){
				yOffset -= 16 * scale;
				xOffset = calculateOffset(i+1);
			}
			else{
				xOffset += 12*scale;
			}

		}
	}

	public void render(int xPos, int yPos, MasterBuffer render){
		int xOffset = calculateOffset(0);
		int yOffset = 0;
		for (int i = 0; i < text.length(); i++){
			String c = text.substring(i, i+1).toLowerCase();
			if (!c.equals(" ")){
				SpriteData cSprite = Engine.getEngine().getSpriteAtlas().get(c);
				if (cSprite != null){
					cSprite.renderSprite(xPos + xOffset, yPos, scale, 0, 1, false, render);
				}
			}
			if (c.equals("\\")){
				yOffset -= 16 * scale;
				xOffset = calculateOffset(i+1);
			}
			xOffset += 12*scale;
		}
	}


	public void setText(String text){
		this.text = text;
	}
}
