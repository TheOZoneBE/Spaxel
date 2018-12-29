package code.graphics;

import code.engine.Engine;
import code.math.VectorD;

import com.fasterxml.jackson.annotation.JsonSetter;

public class SpriteData {
	private VectorD dim;
	private VectorD pos;

	private float[] spriteProperties;
	private Spritesheet spritesheet;
	private int color;

	public SpriteData() {
		super();
	}

	public SpriteData(int width, int height, int xPos, int yPos, Spritesheet spritesheet) {
		this.dim = new VectorD(width, height);
		this.pos = new VectorD(xPos, yPos);
		this.spritesheet = spritesheet;
	}

	public SpriteData(int width, int height, int color) {
		this.dim = new VectorD(width, height);
		this.color = color;
		this.spritesheet = new Spritesheet();
		spriteProperties = new float[] { 0F, 0F, 0F, 0F };
	}

	public void initialize() {
		double sheetXcoord = (double) pos.getValue(0) / spritesheet.getWidth();
		double sheetYcoord = (double) pos.getValue(1) / spritesheet.getHeight();
		double sheetXscale = (double) dim.getValue(0) / spritesheet.getWidth();
		double sheetYscale = (double) dim.getValue(1) / spritesheet.getHeight();
		spriteProperties = new float[] { (float) sheetXcoord, (float) sheetYcoord, (float) sheetXscale,
				(float) sheetYscale };
		color = 0;
	}

	/**
	 * @param dim the dim to set
	 */
	public void setDim(VectorD dim) {
		this.dim = dim;
	}

	public VectorD getDim() {
		return dim;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(VectorD pos) {
		this.pos = pos;
	}

	public VectorD getPos() {
		return pos;
	}

	@JsonSetter("sheetName")
	public void setSpritesheet(String sheetname) {
		this.spritesheet = Engine.getEngine().getSpritesheets().get(sheetname);
	}

	public float[] getSpriteProperties() {
		return spriteProperties;
	}

	public int getSpritesheetID() {
		return spritesheet.getId();
	}

	public Spritesheet getSpritesheet() {
		return spritesheet;
	}

	public int getColor() {
		return color;
	}
}
