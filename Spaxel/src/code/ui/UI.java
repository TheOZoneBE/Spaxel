package code.ui;

import java.util.ArrayList;
import java.util.List;

import code.graphics.Render;
import code.graphics.Sprite;
import code.input.Mouse;

public class UI {
	private Sprite overlay;
	private List<UIElement> elements;

	public UI(Sprite overlay){
		this.overlay = overlay;
		elements = new ArrayList<>();
	}
	
	public void update(Mouse mouse){
		for (UIElement u: elements){
			u.update(mouse.getX(), mouse.getY(), mouse.mouse1);
		}
	}
	
	public void render(Render render){
		overlay.render(0, 0, 0, 0, render);
		for (UIElement u: elements){
			u.render(render);
		}
	}
	
	public void addElement(UIElement element){
		element.setUI(this);
		elements.add(element);
	}
	
	public void removeElement(UIElement element){
		elements.remove(element);
	}
}
