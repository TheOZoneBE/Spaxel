package code.ui;

import code.collision.HitPoint;
import code.collision.HitShape;
import code.graphics.Render;
import code.math.VectorD;

public class UIButton extends UIElement {
	String hoverAction;
	String clickAction;
	HitShape box;

	public UIButton(int x, int y, String hoverAction, String clickAction) {
		super(x, y);
		this.hoverAction = hoverAction;
		this.clickAction = clickAction;
	}

	public void setHitShape(HitShape box) {
		this.box = box;
	}

	public void update(int mouseX, int mouseY, boolean clicked) {
		if (box != null) {
			boolean inside = box.collision(new HitShape(new HitPoint(new VectorD(new double[] { mouseX, mouseY }))));
			if (inside && clicked){
				//do clickaction
			}
			else if (inside){
				//do hoveraction
			}
		}
	}

	public void render(Render render) {
		if (box != null) {
			box.render(x, y, render);
		}
	}

}
