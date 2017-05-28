package code.graphics;

import code.util.BufferUtils;

import java.nio.FloatBuffer;
import java.util.Random;

public class SpriteData {
	private int width;
	private int height;
	private int xPos;
	private int yPos;
	private float[] spriteProperties;
	private float sheetXcoord;
	private float sheetYcoord;
	private float sheetXscale;
	private float sheetYscale;
	private int spritesheetID;
	private Spritesheet spritesheet;
	private int color;
	private Random r;

	public SpriteData(int width, int height, int xPos, int yPos, Spritesheet spritesheet) {
		this.width = width;
		this.height = height;
		this.xPos = xPos;
		this.yPos = yPos;
		this.spritesheetID = spritesheet.getId();
		this.spritesheet = spritesheet;
		sheetXcoord = (float)this.xPos / spritesheet.getWidth();
		sheetYcoord = (float)this.yPos / spritesheet.getHeigth();
		sheetXscale = (float)width / spritesheet.getWidth();
		sheetYscale = (float)height / spritesheet.getHeigth();
		spriteProperties = new float[]{sheetXcoord, sheetYcoord, sheetXscale, sheetYscale};
		color = 0;
		r = new Random();
	}
	
	public SpriteData(int width, int height, int color){
		this.width = width;
		this.height = height;
		this.color = color;
		this.spritesheetID = 0;
		spriteProperties = new float[]{sheetXcoord, sheetYcoord, sheetXscale, sheetYscale};
		r = new Random();
	}

	//new render method, just send all the information to a buffer
	public void renderSprite(int x, int y, int scale, float rot, float transparency, boolean blur, MasterBuffer render){
		float[] trsc = new float[]{
				x, y, width*scale, height*scale
		};

		float[] sinCos = new float[]{
				(float)Math.sin(rot), (float)Math.cos(rot), transparency, color
		};

		render.addNewSprite(spritesheetID, new RenderData(trsc, sinCos, spriteProperties));
	}

	public SpriteData getRandomPart(int width, int height){
		int x = r.nextInt(this.width - width);
		int y = r.nextInt(this.height - height);
		return new SpriteData(width, height, xPos + x, yPos + y, spritesheet);
	}
}
