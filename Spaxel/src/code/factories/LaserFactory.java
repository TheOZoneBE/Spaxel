package code.factories;

import code.entity.Laser;
import code.graphics.Sprite;

public class LaserFactory extends ProjectileFactory{
	
	private int life;
	private double speed;
	
	public LaserFactory(Sprite sprite, int damage, int life, double speed){
		super(sprite, damage);
		this.life = life;
		this.speed = speed;
	}
	
	public Laser make(double x, double y, double rot){
		return new Laser(x,y, rot, sprite, damage, life, speed);
	}

}
