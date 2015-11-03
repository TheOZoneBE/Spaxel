package code.ui;

import java.util.ArrayList;
import java.util.List;

import code.graphics.Render;
import code.graphics.Sprite;

public class UI {
	private Sprite overlay;
	private List<UIElement> elements;

	public UI(Sprite overlay){
		this.overlay = overlay;
		elements = new ArrayList<>();
	}
	
	public void update(){
		for (UIElement u: elements){
			u.update();
		}
	}
	
	public void render(Render render){
		overlay.render(0, 0, 0, 0, render);
		for (UIElement u: elements){
			u.render(render);
		}
	}
}
