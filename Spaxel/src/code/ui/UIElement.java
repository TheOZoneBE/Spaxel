package code.ui;

import code.entity.Entity;
import code.graphics.RenderBuffer;

public class UIElement extends Entity {
	protected UI ui;
	
	
	public UIElement(int x, int y){
		super(x, y, 0);
	}
	
	public void setUI(UI ui){
		this.ui = ui;
	}
	
	public void update(int mouseX, int mouseY, boolean clicked){
		
	}
	
	public void render(RenderBuffer render){
		
	}

}
