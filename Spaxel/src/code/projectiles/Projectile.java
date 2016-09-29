package code.projectiles;

import code.collision.HitPoint;
import code.collision.HitShape;
import code.engine.Engine;
import code.entity.Actor;
import code.entity.Entity;
import code.entity.TrailSegment;
import code.graphics.RenderBuffer;
import code.graphics.SpriteData;
import code.math.VectorF;

public class Projectile extends Entity {
	protected SpriteData sprite;
	protected SpriteData trail;
	protected int damage;
	protected float speed;
	private TrailSegment previous;

	public Projectile(float x, float y, float rot, SpriteData sprite, SpriteData trail, int damage, int life, float speed) {
		super(x, y, rot);
		this.sprite = sprite;
		this.trail = trail;
		this.damage = damage;
		this.life = life;
		this.speed = speed;
		alive = true;
		HitShape hitShape = new HitShape();
		HitPoint hitPoint = new HitPoint(new VectorF(new float[] { 0, 0, 1 }));
		hitShape.addHitPoint(hitPoint);
		setHitShape(hitShape);
	}

	public void update() {
		super.update();
		float dx = (float)Math.sin(rot) * speed;
		float dy = (float)Math.cos(rot) * speed;
		x -= dx*Engine.getEngine().getUpdateTime();
		y -= dy*Engine.getEngine().getUpdateTime();
	}

	@Override
	public void render(int xPos, int yPos, RenderBuffer render) {
		//TODO sprite.render((int) (x + xPos), (int) (y + yPos),rot, render);

	}

	public TrailSegment leaveTrail(){
		previous = new TrailSegment(x, y, rot, trail, previous);
		return previous;
	}

	public SpriteData getSprite() {
		return sprite;
	}
	
	public int getDamage(){
		return damage;
	}

	public void hit(Actor a){
		a.setHealth(a.getHealth() - damage);
		setDead();
	}
}