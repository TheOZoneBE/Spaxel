package code.ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import code.graphics.RenderBuffer;

public class UI{
	private Controller controller;
	private Map<String, UIElement> elements;

	public UI(){
		elements = new HashMap<>();
	}
	
	public void update(){
		controller.update();
		for (UIElement u: elements.values()){
			u.update();
		}
	}
	
	public void render(Graphics g, RenderBuffer render){
		for (UIElement u: elements.values()){
			u.render(g, render);
		}
	}

	public void addElement(String name, UIElement element){
		element.setUI(this);
		elements.put(name, element);
	}

	public UIElement getElement(String name){
		return elements.get(name);
	}
	
	public void removeElement(UIElement element){
		elements.remove(element);
	}
	
	public Controller getController(){
		return controller;
	}
	
	public void setController(Controller controller){
		this.controller = controller;
	}
}
