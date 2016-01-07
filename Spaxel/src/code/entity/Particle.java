package code.entity;

import code.graphics.RenderBuffer;
import code.graphics.Sprite;

public class Particle extends Entity{
	private double deltaRot;
	private double dir;
	private double speed;
	private int life;
	private Sprite sprite;
	private boolean alive;
	
	public Particle(double x, double y, double rot, double deltaRot, double dir, double speed, int life, Sprite sprite){
		super(x, y, rot);
		this.deltaRot = deltaRot;
		this.dir = dir;
		this.speed = speed;
		this.life = life;
		this.sprite = sprite;
		alive = true;
	}
	
	public void update(){
		life--;
		if (life == 0){
			alive = false;
		}
		else{
			rot += deltaRot;
			double dx = Math.sin(dir) * speed;
			double dy = Math.cos(dir) * speed;
			x -= dx;
			y -= dy;
		}		
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void render(int xPos, int yPos, RenderBuffer render){
		sprite.render((int) (getX() + xPos), (int) (getY() + yPos), rot, render);
	}

}
