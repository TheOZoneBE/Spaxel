package code.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import code.entity.Entity;
import code.graphics.RenderBuffer;
import code.ui.UIElement;

public class Label extends UIElement{
	private String text;
	private Font font;
	
	public Label(int x, int y, String text, Font font, float size){
		super(x, y, null);
		this.text = text;
		this.font = font.deriveFont(size);
	}
	
	public void render(RenderBuffer render){
		//TODO text rendering
		/*
		g.setColor(Color.WHITE);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		int width = fm.stringWidth(text);
		int height =fm.getAscent() - fm.getDescent();
		g.drawString(text, (int)x - width/2,(int)y + height/2);*/
	}

	public void render(int xPos, int yPos, RenderBuffer render){
		//TODO text rendering
		/*g.setColor(Color.WHITE);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		int width = fm.stringWidth(text);
		int height =fm.getAscent() - fm.getDescent();
		g.drawString(text, xPos - width/2,yPos + height/2);*/
	}


	public void setText(String text){
		this.text = text;
	}
}
