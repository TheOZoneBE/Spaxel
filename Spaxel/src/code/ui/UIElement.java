package code.ui;

import code.graphics.Render;

public class UIElement {
	protected int x;
	protected int y;
	protected UI ui;
	
	
	public UIElement(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setUI(UI ui){
		this.ui = ui;
	}
	
	public void update(int mouseX, int mouseY, boolean clicked){
		
	}
	
	public void render(Render render){
		
	}

}
