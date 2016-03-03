package code.ui;

import java.util.ArrayList;
import java.util.List;

import code.graphics.RenderBuffer;
import code.input.Mouse;

public class UI{
	private Controller controller;
	private List<UIElement> elements;

	public UI(){
		elements = new ArrayList<>();
	}
	
	public void update(Mouse mouse){
		for (UIElement u: elements){
			u.update(mouse.getX(), mouse.getY(), mouse.mouse1);
		}
	}
	
	public void render(RenderBuffer render){
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
	
	public Controller getController(){
		return controller;
	}
}
