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

	public int calculateOffset(){
		return -(text.length() - 1) * 6 * scale;
	}
	
	public void render(MasterBuffer render){
		int offset = calculateOffset();
		for (int i = 0; i < text.length(); i++){
			String c = text.substring(i, i+1).toLowerCase();
			if (!c.equals(" ")){
				SpriteData cSprite = Engine.getEngine().getSpriteAtlas().get(c);
				if (cSprite != null){
					cSprite.renderSprite((int)(x + offset), (int)y, scale, 0, 1, false, render);
				}
			}
			offset += 12*scale;
		}
	}

	public void render(int xPos, int yPos, MasterBuffer render){
		int offset = calculateOffset();
		for (int i = 0; i < text.length(); i++){
			String c = text.substring(i, i+1).toLowerCase();
			if (!c.equals(" ")){
				SpriteData cSprite = Engine.getEngine().getSpriteAtlas().get(c);
				if (cSprite != null){
					cSprite.renderSprite(xPos + offset, yPos, scale, 0, 1, false, render);
				}
			}
			offset += 12*scale;
		}
	}


	public void setText(String text){
		this.text = text;
	}
}
