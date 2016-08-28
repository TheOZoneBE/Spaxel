package code.entity;


import code.collision.HitShape;
import code.graphics.RenderBuffer;
import code.math.MatrixF;
import code.math.MatrixMaker;

public class Entity {
	protected float x;
	protected float y;
	protected float rot;
	protected HitShape oriHitShape;
	protected HitShape updHitShape;
	protected boolean alive = true;
	protected int life;
	
	public Entity(float x, float y, float rot){
		this.x = x;
		this.y = y;
		this.rot = rot;
	}
	
	public Entity(float x, float y){
		this.x = x;
		this.y = y;
		rot = 0;
	}
	
	public Entity(){
		x = 0;
		y = 0;
		rot = 0;
	}
		
	
	public void render(int xPos, int yPos, RenderBuffer render){
		
	}
	
	public void update(){	
		if(oriHitShape != null){
			updateHitShape();
		}		
	}

	public void reduceLife(){
		if (life > 0){
			life--;
		}
		if (life == 0){
			setDead();
		}
	}
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public float getRot(){
		return rot;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public void setRot(float rot){
		this.rot = rot;
	}
	
	public void updateHitShape(){
		MatrixF updateMatrixF = MatrixMaker.getTransformationMatrix(x, y, rot,1);
		updHitShape = oriHitShape.update(updateMatrixF);
	}
	
	public void setHitShape(HitShape hitShape){
		oriHitShape = hitShape;
	}
	
	public HitShape getUpdHitShape(){
		return updHitShape;
	}
	
	public HitShape getOriHitShape(){
		return oriHitShape;
	}
	
	public boolean collision(Entity e){
		return updHitShape.collision(e.getUpdHitShape());
	}

	public float distanceTo(float xPos, float yPos){
		return (float)Math.sqrt(Math.pow(x-xPos,2) + Math.pow(y-yPos, 2));
	}

	public float distanceTo(Entity e){
		return (float)Math.sqrt(Math.pow(x-e.getX(),2) + Math.pow(y-e.getY(), 2));
	}

	public boolean isAlive(){
		return alive;
	}

	public void setDead(){
		alive = false;
	}


}
