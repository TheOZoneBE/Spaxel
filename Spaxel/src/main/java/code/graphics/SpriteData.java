package code.graphics;

import code.engine.Engine;
import code.math.VectorF;
import com.fasterxml.jackson.annotation.JsonSetter;

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

	private SpriteData(){

	}

	public SpriteData(int width, int height, int xPos, int yPos, Spritesheet spritesheet) {
		this.width = width;
		this.height = height;
		this.xPos = xPos;
		this.yPos = yPos;
		this.spritesheet = spritesheet;
		initialize();
	}

	public void initialize(){
		this.spritesheetID = spritesheet.getId();
		sheetXcoord = (float)this.xPos*width / spritesheet.getWidth();
		sheetYcoord = (float)this.yPos*height / spritesheet.getHeight();
		sheetXscale = (float)width / spritesheet.getWidth();
		sheetYscale = (float)height / spritesheet.getHeight();
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
	//TODO depricate
	public void renderSprite(int x, int y, int scale, float rot, float transparency, boolean blur, MasterBuffer render){
		float[] trsc = new float[]{
				x, y, width*scale, height*scale
		};

		float[] sinCos = new float[]{
				(float)Math.sin(rot), (float)Math.cos(rot), transparency, color
		};

		render.addNewSprite(spritesheetID, new RenderData(trsc, sinCos, spriteProperties));
	}

	//again a new render method
	//TODO temporary: render rework move this out of spritedata
	public void renderSprite(VectorF pos, int scale, float rot, float transparency, MasterBuffer render){
		float[] trsc = new float[]{
				pos.getValue(0), pos.getValue(1), width*scale, height*scale
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	@JsonSetter("sheetName")
	public void setSpritesheet(String sheetname){
		this.spritesheet = Engine.getEngine().getSpritesheets().get(sheetname);
	}
}
