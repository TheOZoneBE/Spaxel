package code.ui;

import code.engine.Engine;
import code.graphics.MasterBuffer;
import code.graphics.RenderData;
import code.graphics.RenderLayer;
import code.graphics.SpriteData;
import code.math.VectorF;

public class UILabel extends UIElement{
	private String text;
	private float scale;
	private boolean alignLeft;

	public void render(MasterBuffer buffer){
		VectorF offset;
		if (alignLeft){
			offset = new VectorF(0,0);
		}
		else {
			offset = new VectorF(calculateOffset(0), 0);
		}

		for (int i = 0; i < text.length(); i++){
			String c = text.substring(i, i+1).toLowerCase();
			if (!c.equals(" ")){
				SpriteData cSprite = Engine.getEngine().getSpriteAtlas().get(c);
				if (cSprite != null){
					RenderData data = new RenderData();
					data.setPos(position.getCoord().sum(offset));
					data.setRot(position.getRot());
					data.setXScale(scale*cSprite.getWidth());
					data.setYScale(scale*cSprite.getHeight());
					data.setSpriteSheetID(cSprite.getSpritesheetID());
					data.setTexOffset(cSprite.getSpriteProperties());
					buffer.addNewSprite(RenderLayer.UI, data);
				}
			}
			if (c.equals("\\")){
				if (alignLeft){
					offset.setValue(0, 0);
				}
				else{
					offset.setValue(0, calculateOffset(i+1));
				}

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

	private float calculateOffset(int i){
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
