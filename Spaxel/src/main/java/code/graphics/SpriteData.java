package code.graphics;

import code.engine.Engine;
import com.fasterxml.jackson.annotation.JsonSetter;

public class SpriteData {
	private int width;
	private int height;
	private int xPos;
	private int yPos;
	private int xPosRel;
	private int yPosRel;

	private float[] spriteProperties;
	private float sheetXcoord;
	private float sheetYcoord;
	private float sheetXscale;
	private float sheetYscale;
	private int spritesheetID;
	private Spritesheet spritesheet;
	private int color;

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
		if (xPosRel!= 0) xPos = xPosRel*width;
		if (yPosRel!= 0) yPos = yPosRel*height;
		sheetXcoord = (float)this.xPos / spritesheet.getWidth();
		sheetYcoord = (float)this.yPos / spritesheet.getHeight();
		sheetXscale = (float)width / spritesheet.getWidth();
		sheetYscale = (float)height / spritesheet.getHeight();
		spriteProperties = new float[]{sheetXcoord, sheetYcoord, sheetXscale, sheetYscale};
		color = 0;
	}
	
	public SpriteData(int width, int height, int color){
		this.width = width;
		this.height = height;
		this.color = color;
		this.spritesheetID = 0;
		spriteProperties = new float[]{sheetXcoord, sheetYcoord, sheetXscale, sheetYscale};
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

	public int getxPosRel() {
		return xPosRel;
	}

	public void setxPosRel(int xPosRel) {
		this.xPosRel = xPosRel;
	}

	public int getyPosRel() {
		return yPosRel;
	}

	public void setyPosRel(int yPosRel) {
		this.yPosRel = yPosRel;
	}

	@JsonSetter("sheetName")
	public void setSpritesheet(String sheetname){
		this.spritesheet = Engine.getEngine().getSpritesheets().get(sheetname);
	}

	public float[] getSpriteProperties() {
		return spriteProperties;
	}

	public int getSpritesheetID() {
		return spritesheetID;
	}

	public Spritesheet getSpritesheet(){
		return spritesheet;
	}

	public int getColor(){
		return color;
	}
}
