package code.factories;

import code.projectiles.BasicLaser;
import code.graphics.Sprite;

public class BasicLaserFactory extends ProjectileFactory{
	

	
	public BasicLaserFactory(Sprite sprite,Sprite trail, int damage, int life, double speed){
		super(sprite,trail, damage, life, speed);
	}
	
	public BasicLaser make(double x, double y, double rot){
		return new BasicLaser(x,y, rot, sprite, trail,damage, life, speed);
	}

}
