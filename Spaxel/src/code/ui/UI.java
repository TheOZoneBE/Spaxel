package code.ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import code.engine.Engine;
import code.entity.Label;
import code.graphics.RenderBuffer;
import code.input.Mouse;

public class UI{
	private Controller controller;
	private Map<String, UIElement> elements;
	private List<Label> labels;
	private Engine engine;

	public UI(Engine engine){
		this.engine = engine;
		elements = new HashMap<>();
		labels = new ArrayList<>();
	}
	
	public void update(){
		for (UIElement u: elements.values()){
			u.update();
		}
	}
	
	public void render(RenderBuffer render){
		for (UIElement u: elements.values()){
			u.render(render);
		}
	}
	
	public void drawText(Graphics g){
		for(Label l: labels){
			l.render(g);
		}
	}
	
	public void addLabel(Label label){
		labels.add(label);
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
		controller.setEngine(engine);
	}
}
