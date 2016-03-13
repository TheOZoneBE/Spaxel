package code.ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import code.engine.Engine;
import code.entity.Label;
import code.graphics.RenderBuffer;
import code.input.Mouse;

public class UI{
	private Controller controller;
	private List<UIElement> elements;
	private List<Label> labels;
	private Engine engine;

	public UI(Engine engine){
		this.engine = engine;
		elements = new ArrayList<>();
		labels = new ArrayList<>();
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
	
	public void drawText(Graphics g){
		for(Label l: labels){
			l.render(g);
		}
	}
	
	public void addLabel(Label label){
		labels.add(label);
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
	
	public void setController(Controller controller){
		this.controller = controller;
		controller.setEngine(engine);
	}
}
