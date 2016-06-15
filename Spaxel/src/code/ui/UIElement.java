package code.ui;

import code.entity.Entity;
import code.graphics.RenderBuffer;
import code.graphics.Sprite;

import java.awt.*;

public class UIElement extends Entity {
	protected UI ui;
	protected Sprite sprite;
	
	
	public UIElement(int x, int y, Sprite sprite){
		super(x, y);
		this.sprite = sprite;
		life = -1;
	}
	
	public UIElement(int x, int y, double rot, Sprite sprite){
		super(x, y, rot);
		this.sprite = sprite;
	}
	
	public void setUI(UI ui){
		this.ui = ui;
	}
	
	public void update(){
		
	}
	
	public void render(Graphics g, RenderBuffer render){
		sprite.render((int)x,(int)y, render);
	}

}
