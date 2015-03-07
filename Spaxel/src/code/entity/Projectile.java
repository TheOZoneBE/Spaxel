package code.entity;

import code.graphics.Render;
import code.graphics.Sprite;

public class Projectile extends Entity {
	private Sprite sprite;
	private boolean alive;

	public Projectile(int x, int y, Sprite sprite) {
		super(x, y);
		this.sprite = sprite;
		alive = true;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setDead() {
		alive = false;
	}

	@Override
	public void render(int xPos, int yPos, Render render) {
		sprite.render((int) (getX() + xPos), (int) (getY() + yPos), render);

	}

	public Sprite getSprite() {
		return sprite;
	}

}