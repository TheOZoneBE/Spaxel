package code.entity;

import code.graphics.Render;
import code.graphics.Sprite;

public class Laser extends Projectile {

	public Laser(double x, double y, double rot, Sprite sprite, int life, double speed) {
		super(x, y, rot, sprite);
		this.life = life;
		this.speed = speed;
	}

	private int life;
	private double speed;

	public void update() {
		super.update();
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
