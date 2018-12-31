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

	public SpriteData(VectorD dim, VectorD pos, Spritesheet spritesheet) {
		this.dim = dim;
		this.pos = pos;
		this.spritesheet = spritesheet;
	}

	public SpriteData(VectorD dim, int color) {
		this.dim = dim;
		this.color = color;
		this.spritesheet = new Spritesheet();
		spriteProperties = new float[] {0F, 0F, 0F, 0F};
	}

	public void initialize() {
		if (spritesheet != null) {
			double sheetXcoord = pos.getValue(0) / spritesheet.getWidth();
			double sheetYcoord = pos.getValue(1) / spritesheet.getHeight();
			double sheetXscale = dim.getValue(0) / spritesheet.getWidth();
			double sheetYscale = dim.getValue(1) / spritesheet.getHeight();
			spriteProperties = new float[] {(float) sheetXcoord, (float) sheetYcoord,
					(float) sheetXscale, (float) sheetYscale};
			color = 0;
		} else {
			spritesheet = new Spritesheet();
			spriteProperties = new float[] {0F, 0F, 0F, 0F};
		}
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

	@JsonSetter("color")
	public void setColorValue(String colorValue) {
		color = Integer.parseUnsignedInt(colorValue, 16);
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
