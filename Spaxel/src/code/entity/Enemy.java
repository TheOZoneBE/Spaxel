package code.entity;

import code.graphics.RenderBuffer;
import code.graphics.Sprite;

public class Enemy extends Entity{
	private Sprite sprite;

	public Enemy(double x, double y, double rot, Sprite sprite) {
		super(x, y, rot);
		this.sprite = sprite;
	}
	
	public void update(){
		super.update();
	}
	
	@Override
	public void render(int xPos, int yPos, RenderBuffer render) {
		sprite.render((int) (getX() + xPos), (int) (getY() + yPos), rot, render);
		
	}

}
