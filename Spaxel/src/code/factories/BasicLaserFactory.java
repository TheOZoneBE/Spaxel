package code.factories;

import code.projectiles.BasicLaser;
import code.graphics.Sprite;

public class BasicLaserFactory extends ProjectileFactory{
	

	
	public BasicLaserFactory(Sprite sprite,Sprite trail, int damage, int life, float speed){
		super(sprite,trail, damage, life, speed);
	}
	
	public BasicLaser make(float x, float y, float rot){
		return new BasicLaser(x,y, rot, sprite, trail,damage, life, speed);
	}

}
