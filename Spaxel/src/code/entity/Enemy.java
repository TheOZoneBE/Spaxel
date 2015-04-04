package code.entity;

import code.graphics.Render;
import code.graphics.Sprite;

public class Enemy extends Entity{
	private double rot;
	private Sprite sprite;

	public Enemy(int x, int y, Sprite sprite) {
		super(x, y);
		rot = 0;
		this.sprite = sprite;
	}
	
	public void update(){
		// TODO lay basics of AI
	}
	
	@Override
	public void render(int xPos, int yPos, Render render) {
		sprite.render((int) (getX() + xPos), (int) (getY() + yPos), rot, render);
		
	}

}
