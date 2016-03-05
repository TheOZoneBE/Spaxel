package code.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import code.entity.Entity;

public class Label extends Entity{
	private String text;
	private Font font;
	
	public Label(double x, double y, String text, Font font, float size){
		super(x, y);
		this.text = text;
		this.font = font.deriveFont(size);
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		int width = fm.stringWidth(text);
		int height =fm.getAscent() - fm.getDescent();
		g.drawString(text, (int)x - width/2,(int)y + height/2);
	}
}
