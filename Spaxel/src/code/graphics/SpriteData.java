package code.graphics;

import code.util.BufferUtils;

import java.nio.FloatBuffer;
import java.util.Random;

public class SpriteData {
	private int width;
	private int height;
	private int xPos;
	private int yPos;
	private float sheetXcoord;
	private float sheetYcoord;
	private float sheetXscale;
	private float sheetYscale;
	private Spritesheet spritesheet;
	private int color;
	private Random r;

	public SpriteData(int width, int height, int xPos, int yPos, Spritesheet spritesheet) {
		this.width = width;
		this.height = height;
		this.xPos = xPos*width;
		this.yPos = yPos*height;
		this.spritesheet = spritesheet;
		sheetXcoord = (float)this.xPos / spritesheet.getWidth();
		sheetYcoord = (float)this.yPos / spritesheet.getHeigth();
		sheetXscale = (float)width / spritesheet.getWidth();
		sheetYscale = (float)height / spritesheet.getHeigth();
		color = 0;
		r = new Random();
	}
	
	public SpriteData(int width, int height, int color){
		this.width = width;
		this.height = height;
		this.color = color;
		r = new Random();
	}

	public FloatBuffer getProperties(){
		return BufferUtils.createFloatBuffer(new float[]{sheetXcoord, sheetYcoord, sheetXscale, sheetYscale});
	}


	//new render method, just send all the information to a buffer
	public void renderSprite(int x, int y, int scale, float rot, float transparency, boolean blur, MasterBuffer render){

		FloatBuffer trsc  = BufferUtils.createFloatBuffer(new float[]{
				x, y, width*scale, height*scale
		});

		FloatBuffer sinCos =BufferUtils.createFloatBuffer(new float[]{
				(float)Math.sin(rot), (float)Math.cos(rot), transparency, color
		});

		render.addNewSprite(spritesheet.getId(), trsc, sinCos, getProperties());
	}

/*
	//TODO rewrite this to work with opengl textures
	public SpriteData getRandomPart(int width, int height){
		int[] rPixels = new int[width * height];
		int x = r.nextInt(this.width - width);
		int y = r.nextInt(this.height - height);
		for (int i = 0; i < width; i++){
			for (int j = 0; j< height; j++){
				rPixels[i + j*width] = pixels[x + i + (y+j)*this.width];
			}
		}
		return new SpriteData(width, height, scale , rPixels);
	}
*/
}
