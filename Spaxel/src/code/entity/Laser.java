package code.entity;

import code.graphics.Render;
import code.graphics.Sprite;

public class Laser extends Projectile {

	public Laser(int x, int y, Sprite sprite, double rot, int life, double speed) {
		super(x, y, sprite);
		this.rot = rot;
		this.life = life;
		this.speed = speed;
	}

	private double rot;
	private int life;
	private double speed;

	public void update() {
		if (life > 0) {
			double dx = Math.sin(rot) * speed;
			double dy = Math.cos(rot) * speed;
			setX(getX() - dx);
			setY(getY() - dy);
			life--;
		} else {
			setDead();
		}
	}

	@Override
	public void render(int xPos, int yPos, Render render) {
		getSprite().render((int) (getX() + xPos), (int) (getY() + yPos), rot, render);

	}

}
