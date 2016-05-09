package code.factories;

import code.projectiles.BasicLaser;
import code.graphics.Sprite;

public class BasicLaserFactory extends ProjectileFactory{
	

	
	public BasicLaserFactory(Sprite sprite, int damage, int life, double speed){
		super(sprite, damage, life, speed);
	}
	
	public BasicLaser make(double x, double y, double rot){
		return new BasicLaser(x,y, rot, sprite, damage, life, speed);
	}

}
