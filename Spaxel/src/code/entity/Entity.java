package code.entity;

import code.graphics.Render;
import code.graphics.Sprite;

public abstract class Entity {
	private Sprite sprite;
	
	public Entity(Sprite sprite){
		this.sprite = sprite;		
	}
	
	public abstract void update();
	
	public abstract void render(Render render);
}
