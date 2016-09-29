package code.entity;

import code.engine.Engine;
import code.graphics.RenderBuffer;
import code.graphics.SpriteData;

public class Particle extends Entity{
	private float deltaRot;
	private float dir;
	private float speed;
	private SpriteData sprite;
	
	public Particle(float x, float y, float rot, float deltaRot, float dir, float speed, int life, SpriteData sprite){
		super(x, y, rot);
		this.deltaRot = deltaRot;
		this.dir = dir;
		this.speed = speed;
		this.life = life;
		this.sprite = sprite;
	}
	
	public void update(){
		rot += deltaRot;
		float dx = (float)Math.sin(dir) * speed;
		float dy = (float)Math.cos(dir) * speed;
		x -= dx* Engine.getEngine().getUpdateTime();
		y -= dy*Engine.getEngine().getUpdateTime();
	}
	
	public void render(int xPos, int yPos, RenderBuffer render){
		//TODO sprite.render((int) (x + xPos), (int) (y + yPos), rot, render);
	}

}
