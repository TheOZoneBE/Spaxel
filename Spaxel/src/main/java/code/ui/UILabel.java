package code.ui;

import code.engine.Engine;
import code.graphics.MasterBuffer;
import code.graphics.SpriteData;
import code.math.VectorF;

public class UILabel extends UIElement{
	private String text;
	private int scale;

	public void render(MasterBuffer buffer){
		VectorF offset = new VectorF(calculateOffset(0), 0);
		for (int i = 0; i < text.length(); i++){
			String c = text.substring(i, i+1).toLowerCase();
			if (!c.equals(" ")){
				SpriteData cSprite = Engine.getEngine().getSpriteAtlas().get(c);
				if (cSprite != null){
					cSprite.renderSprite(position.getCoord().sum(offset), scale, position.getRot(), 1, buffer);
				}
			}
			if (c.equals("\\")){
				offset.setValue(0, calculateOffset(i+1));
				offset.setValue(1, offset.getValue(1)- 16 *scale);
			}
			else{
				offset.setValue(0,offset.getValue(0) + 12* scale);
			}

		}
		for(UIElement child: children){
			child.render(buffer);
		}
	}

	private int calculateOffset(int i){
		int index = text.indexOf('\\', i);
		if (index == -1) {
			index = text.length();
		}
		return (1-index + i) * 6 * scale;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}
}
