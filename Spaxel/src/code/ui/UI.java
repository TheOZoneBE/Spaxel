package code.ui;

import java.awt.Graphics;
import java.util.*;

import code.engine.Engine;
import code.engine.EntityType;
import code.entity.Entity;
import code.entity.Player;
import code.graphics.MasterBuffer;
import code.graphics.RenderBuffer;
import code.inventory.Item;

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
	
	public void render( MasterBuffer render){
		for (UIElement u: elements.values()){
			u.render(render);
		}
		List<Entity> players = Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER);
		if (!players.isEmpty()){
			Player player = (Player)players.get(0);
			Iterator<Item> toRender = player.getItemIterator(EntityType.MOUSE1ITEM);
			int i = 0;
			while (toRender.hasNext()){
				Item e = toRender.next();
				e.render(40, 40 + i * 72,  render);
				i++;
			}

			toRender = player.getItemIterator(EntityType.MOUSE3ITEM);
			i= 0;
			while (toRender.hasNext()){
				Item e = toRender.next();
				e.render(1240, 40 + i * 72, render);
				i++;
			}

			toRender = player.getItemIterator(EntityType.SHIPITEM);
			i= 0;
			while (toRender.hasNext()){
				Item e = toRender.next();
				e.render(386 + i * 72, 680,  render);
				i++;
			}
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
