package code.ui;

import code.engine.Engine;
import code.graphics.MasterBuffer;
import code.graphics.RenderData;
import code.graphics.RenderLayer;
import code.graphics.SpriteData;
import code.math.VectorD;

import java.util.Map;

public class UILabel extends UIElement {
	private static final String SPACE = " ";
	private static final String NEWLINE = "\\";
	private static final int SPACING = 10;
	private static final int TWO = 2;
	private static final int NEWLINE_OFFSET = 16;
	private String text;
	private double scale;
	private boolean alignLeft;

	public UILabel() {
		super();
	}

	public void render(MasterBuffer buffer) {
		VectorD offset;
		if (alignLeft) {
			offset = new VectorD(0, 0);
		} else {
			offset = new VectorD(calculateOffset(0), 0);
		}

		for (int i = 0; i < text.length(); i++) {
			String c = text.substring(i, i + 1);
			SpriteData cSprite = Engine.getEngine().getSpriteAtlas().get(c);
			if (!c.equals(SPACE) && cSprite != null) {
				RenderData data = new RenderData();
				data.setPos(position.getCoord().sum(offset));
				data.setRot(position.getRot());
				data.setXScale(scale * cSprite.getWidth());
				data.setYScale(scale * cSprite.getHeight());
				data.setSpriteSheetID(cSprite.getSpritesheetID());
				data.setTexOffset(cSprite.getSpriteProperties());
				buffer.addNewSprite(RenderLayer.UI, data);
			}
			if (c.equals(NEWLINE)) {
				if (alignLeft) {
					offset.setValue(0, 0);
				} else {
					offset.setValue(0, calculateOffset(i + 1));
				}

				offset.setValue(1, offset.getValue(1) - NEWLINE_OFFSET * scale);
			} else {
				if (cSprite != null) {
					offset.setValue(0, offset.getValue(0) + cSprite.getWidth() * scale);
				} else {
					offset.setValue(0, offset.getValue(0) + SPACING * scale);
				}

			}

		}
		for (UIElement child : children) {
			child.render(buffer);
		}
	}

	private double calculateOffset(int i) {
		Map<String, SpriteData> sprites = Engine.getEngine().getSpriteAtlas();
		int index = text.indexOf('\\', i);
		if (index == -1) {
			index = text.length();
		}
		double length = 0;
		int start = 0;
		for (int k = i; k < index; k++) {
			SpriteData sprite = sprites.get(text.substring(k, k + 1));
			int w;
			if (sprite != null) {
				w = sprite.getWidth();
			} else {
				w = SPACING;
			}
			if (k == i) {
				start = w / TWO;
			}
			length += w;
		}
		return (-length / TWO + start) * scale;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public boolean isAlignLeft() {
		return alignLeft;
	}

	public void setAlignLeft(boolean alignLeft) {
		this.alignLeft = alignLeft;
	}
}
