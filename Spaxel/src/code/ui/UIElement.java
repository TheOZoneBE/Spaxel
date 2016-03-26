package code.ui;

import code.entity.Entity;
import code.graphics.RenderBuffer;
import code.graphics.Sprite;

public class UIElement extends Entity {
	protected UI ui;
	protected Sprite sprite;
	
	
	public UIElement(int x, int y, Sprite sprite){
		super(x, y);
		this.sprite = sprite;
	}
	
	public UIElement(int x, int y, double rot, Sprite sprite){
		super(x, y, rot);
		this.sprite = sprite;
	}
	
	public void setUI(UI ui){
		this.ui = ui;
	}
	
	public void update(int mouseX, int mouseY, boolean clicked){
		
	}
	
	public void render(RenderBuffer render){
		sprite.render((int)x,(int)y, render);
	}

}
