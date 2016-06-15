package code.entity;

import code.engine.Engine;
import code.graphics.RenderBuffer;
import code.graphics.Sprite;

public class Particle extends Entity{
	private double deltaRot;
	private double dir;
	private double speed;
	private Sprite sprite;
	
	public Particle(double x, double y, double rot, double deltaRot, double dir, double speed, int life, Sprite sprite){
		super(x, y, rot);
		this.deltaRot = deltaRot;
		this.dir = dir;
		this.speed = speed;
		this.life = life;
		this.sprite = sprite;
	}
	
	public void update(){
		rot += deltaRot;
		double dx = Math.sin(dir) * speed;
		double dy = Math.cos(dir) * speed;
		x -= dx* Engine.getEngine().getUpdateTime();
		y -= dy*Engine.getEngine().getUpdateTime();
	}
	
	public void render(int xPos, int yPos, RenderBuffer render){
		sprite.render((int) (x + xPos), (int) (y + yPos), rot, render);
	}

}
