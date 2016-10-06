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
	private Random r;

	public SpriteData(int width, int height, int xPos, int yPos, Spritesheet spritesheet) {
		this.width = width;
		this.height = height;
		this.xPos = xPos;
		this.yPos = yPos;
		this.spritesheet = spritesheet;
		sheetXcoord = (float)xPos / spritesheet.getWidth();
		sheetYcoord = (float)yPos / spritesheet.getHeigth();
		sheetXscale = (float)width / spritesheet.getWidth();
		sheetYscale = (float)height / spritesheet.getHeigth();
		r = new Random();
	}

	/*
	public SpriteData(int width, int height, int scale, int[] pixels){
		this.width = width;
		this.height = height;
		r = new Random();
		//loadBlur();
	}
	
	public SpriteData(int width, int height, int scale, int color){
		this.width = width;
		this.height = height;
		this.scale = scale;
		r = new Random();
	}*/

	public FloatBuffer getProperties(){
		return BufferUtils.createFloatBuffer(new float[]{sheetXcoord, sheetYcoord, sheetXscale, sheetYscale});
	}


	//new render method, just send all the information to a buffer
	public void renderSprite(int x, int y, int scale, float rot, float transparency, boolean blur, RenderBuffer render){
		render.addTrsc(BufferUtils.createFloatBuffer(new float[]{
				x, y, width*scale, height*scale
		}));

		render.addSinCos(BufferUtils.createFloatBuffer(new float[]{
				(float)Math.sin(rot), (float)Math.cos(rot), transparency, 0
		}));

		render.addTexOffset(getProperties());
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
