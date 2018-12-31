package code.ui.elements;

import code.engine.Engine;
import code.graphics.MasterBuffer;
import code.graphics.RenderData;
import code.graphics.RenderLayer;
import code.graphics.SpriteData;
import code.math.VectorD;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "uilabel")
public class UILabel extends UIElement {
	private static final String SPACE = " ";
	private static final String NEWLINE = "\\\\";
	private static final String L_BRACKET = "{";
	private static final String R_BRACKET = "}";

	private static final int SPACING = 10;
	private static final int TWO = 2;
	private static final int NEWLINE_OFFSET = 16;
	private String text;
	private double scale;
	private boolean alignLeft;

	public UILabel() {
		super();
	}

	@Override
	public void render(MasterBuffer buffer) {
		String[] lines = text.split(NEWLINE);
		VectorD offset = new VectorD(0, 0);
		for (String line : lines) {
			List<Character> characters = getCharacters(line);
			if (!alignLeft) {
				offset.setValue(0, calculateOffset(characters) * scale);
			}

			for (Character character : characters) {
				VectorD charOffset = offset.sum(new VectorD(character.getWidth() * scale / TWO, 0));
				character.render(position.getCoord().sum(charOffset), scale, buffer);
				offset.setValue(0, offset.getValue(0) + character.getWidth() * scale);
			}
			offset.setValue(1, offset.getValue(1) - NEWLINE_OFFSET * scale);
		}

	}

	private List<Character> getCharacters(String line) {
		List<Character> characters = new ArrayList<>();
		int i = 0;
		while (i < line.length()) {
			String c = line.substring(i, i + 1);
			if (c.equals(L_BRACKET)) {
				int start = i + 1;
				while (!c.equals(R_BRACKET)) {
					i++;
					c = line.substring(i, i + 1);
				}
				characters.add(new Character(Engine.getEngine().getSpriteAtlas().get(line.substring(start, i))));

			} else if (c.equals(SPACE)) {
				characters.add(new Character(SPACING));

			} else {
				characters.add(new Character(Engine.getEngine().getSpriteAtlas().get(c)));
			}
			i++;

		}
		return characters;
	}

	private static double calculateOffset(List<Character> line) {
		return -calculateLineWidth(line) / TWO;
	}

	private static double calculateLineWidth(List<Character> line) {
		return line.stream().map(Character::getWidth).reduce(0., (Double accWidth, Double width) -> accWidth += width);
	}

	private static class Character {
		private double width;
		private SpriteData sprite;

		public Character(SpriteData sprite) {
			this.sprite = sprite;
			this.width = sprite.getDim().getValue(0);
		}

		public Character(double width) {
			this.width = width;
		}

		public double getWidth() {
			return width;
		}

		public void render(VectorD position, double scale, MasterBuffer buffer) {
			if (sprite != null) {
				RenderData data = new RenderData();
				data.setPos(position);
				data.setRot(0);
				data.setScale(sprite.getDim().multiplicate(scale));
				data.setSpriteSheetID(sprite.getSpritesheetID());
				data.setTexOffset(sprite.getSpriteProperties());
				buffer.addNewSprite(RenderLayer.UI, data);
			}
		}
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
