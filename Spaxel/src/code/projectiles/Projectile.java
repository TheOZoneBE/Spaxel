package code.projectiles;

import code.collision.HitPoint;
import code.collision.HitShape;
import code.engine.Engine;
import code.engine.EntityType;
import code.entity.Enemy;
import code.entity.Entity;
import code.entity.TrailSegment;
import code.graphics.RenderBuffer;
import code.graphics.Sprite;
import code.math.VectorD;

public class Projectile extends Entity {
	protected Sprite sprite;
	protected boolean alive;
	protected int damage;
	protected int life;
	protected double speed;
	private TrailSegment previous;

	public Projectile(double x, double y, double rot, Sprite sprite, int damage, int life, double speed) {
		super(x, y, rot);
		this.sprite = sprite;
		this.damage = damage;
		this.life = life;
		this.speed = speed;
		alive = true;
		HitShape hitShape = new HitShape();
		HitPoint hitPoint = new HitPoint(new VectorD(new double[] { 0, 0, 1 }));
		hitShape.addHitPoint(hitPoint);
		setHitShape(hitShape);
		previous = new TrailSegment(x, y, rot, 0xffffffff, null);
		Engine.getEngine().getEntityStream().addEntity(EntityType.TRAILSEGMENT, previous);
	}

	public boolean isAlive() {
		return alive;
	}

	public void setDead() {
		alive = false;
	}

	public void update() {
		super.update();
		if (life > 0) {
			double dx = Math.sin(rot) * speed;
			double dy = Math.cos(rot) * speed;
			setX(getX() - dx);
			setY(getY() - dy);
			life--;
			previous = new TrailSegment(x, y, rot, 0xffffffff, previous);
			Engine.getEngine().getEntityStream().addEntity(EntityType.TRAILSEGMENT, previous);
		} else {
			setDead();
		}
	}

	@Override
	public void render(int xPos, int yPos, RenderBuffer render) {
		sprite.render((int) (getX() + xPos), (int) (getY() + yPos),rot, render);

	}

	public Sprite getSprite() {
		return sprite;
	}
	
	public int getDamage(){
		return damage;
	}

	public void hit(Enemy e){
		e.setHealth(e.getHealth() - damage);
		setDead();
	}
}