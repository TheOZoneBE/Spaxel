package code.entity;

import code.collision.HitPoint;
import code.collision.HitShape;
import code.graphics.RenderBuffer;
import code.graphics.Sprite;
import code.math.VectorD;

public class Projectile extends Entity {
	private Sprite sprite;
	private boolean alive;
	private int damage;

	public Projectile(double x, double y, double rot, Sprite sprite, int damage) {
		super(x, y, rot);
		this.sprite = sprite;
		this.damage = damage;
		alive = true;
		HitShape hitShape = new HitShape();
		HitPoint hitPoint = new HitPoint(new VectorD(new double[] { 0, 0, 1 }));
		hitShape.addHitPoint(hitPoint);
		setHitShape(hitShape);
	}

	public boolean isAlive() {
		return alive;
	}

	public void setDead() {
		alive = false;
	}
	
	public void update(){
		super.update();
	}

	@Override
	public void render(int xPos, int yPos, RenderBuffer render) {
		sprite.render((int) (getX() + xPos), (int) (getY() + yPos), render);

	}

	public Sprite getSprite() {
		return sprite;
	}
	
	public int getDamage(){
		return damage;
	}

}