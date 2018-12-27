package code.ui;

import code.engine.Engine;
import code.graphics.MasterBuffer;
import code.graphics.RenderData;
import code.graphics.RenderLayer;
import code.graphics.SpriteData;
import code.math.VectorF;

import java.util.Map;

public class UILabel extends UIElement {
	private static final String SPACE = " ";
	private static final String NEWLINE = "\\";
	private String text;
	private float scale;
	private boolean alignLeft;

	public UILabel() {
		super();
	}

	public void render(MasterBuffer buffer) {
		VectorF offset;
		if (alignLeft) {
			offset = new VectorF(0, 0);
		} else {
			offset = new VectorF(calculateOffset(0), 0);
		}

		for (int i = 0; i < text.length(); i++) {
			String c = text.substring(i, i + 1);
			SpriteData cSprite = null;
			if (!c.equals(SPACE)) {
				cSprite = Engine.getEngine().getSpriteAtlas().get(c);
				if (cSprite != null) {
					RenderData data = new RenderData();
					data.setPos(position.getCoord().sum(offset));
					data.setRot(position.getRot());
					data.setXScale(scale * cSprite.getWidth());
					data.setYScale(scale * cSprite.getHeight());
					data.setSpriteSheetID(cSprite.getSpritesheetID());
					data.setTexOffset(cSprite.getSpriteProperties());
					buffer.addNewSprite(RenderLayer.UI, data);
				}
			}
			if (c.equals(NEWLINE)) {
				if (alignLeft) {
					offset.setValue(0, 0);
				} else {
					offset.setValue(0, calculateOffset(i + 1));
				}

				offset.setValue(1, offset.getValue(1) - 16 * scale);
			} else {
				if (cSprite != null) {
					offset.setValue(0, offset.getValue(0) + cSprite.getWidth() * scale);
				} else {
					offset.setValue(0, offset.getValue(0) + 10 * scale);
				}

			}

		}
		for (UIElement child : children) {
			child.render(buffer);
		}
	}

	private float calculateOffset(int i) {
		Map<String, SpriteData> sprites = Engine.getEngine().getSpriteAtlas();
		int index = text.indexOf('\\', i);
		if (index == -1) {
			index = text.length();
		}
		int length = 0;
		int start = 0;
		for (int k = i; k < index; k++) {
			SpriteData sprite = sprites.get(text.substring(k, k + 1));
			int w = 10;
			if (sprite != null) {
				w = sprite.getWidth();
			}
			if (k == i) {
				start = w / 2;
			}
			length += w;
		}
		return (-length / 2 + start) * scale;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public boolean isAlignLeft() {
		return alignLeft;
	}

	public void setAlignLeft(boolean alignLeft) {
		this.alignLeft = alignLeft;
	}
}
