package code.projectiles;

import code.collision.HitPoint;
import code.collision.HitShape;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.entity.Actor;
import code.entity.Entity;
import code.entity.TrailSegment;
import code.factories.entities.TrailSegmentIndustry;
import code.graphics.MasterBuffer;
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
		x += dx*Engine.getEngine().getUpdateTime();
		y += dy*Engine.getEngine().getUpdateTime();
	}

	@Override
	public void render(int xPos, int yPos, MasterBuffer render) {
		sprite.renderSprite((int) (x + xPos), (int) (y + yPos),2, rot, 1, false, render);

	}

	public NEntity leaveTrail(){
		//TODO reimplement trajectory if needed
		//previous = new TrailSegment(x, y, rot, trail, previous);
		TrailSegmentIndustry tsi = (TrailSegmentIndustry)Engine.getEngine().getIndustryMap().get("trail_segment_industry");
		return tsi.produce(new PositionComponent(new VectorF(x, y), rot),new SpriteComponent(trail,2));
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