package code.entity;

import code.graphics.RenderBuffer;
import code.graphics.Sprite;

public class Projectile extends Entity {
	private Sprite sprite;
	private boolean alive;

	public Projectile(double x, double y, double rot, Sprite sprite) {
		super(x, y, rot);
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
	public void render(int xPos, int yPos, RenderBuffer render) {
		sprite.render((int) (getX() + xPos), (int) (getY() + yPos), render);

	}

	public Sprite getSprite() {
		return sprite;
	}

}