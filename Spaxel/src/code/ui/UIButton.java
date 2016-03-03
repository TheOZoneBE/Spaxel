package code.ui;

import java.lang.reflect.Method;

import code.collision.HitPoint;
import code.collision.HitShape;
import code.graphics.RenderBuffer;
import code.graphics.Sprite;
import code.math.MatrixMaker;
import code.math.VectorD;

public class UIButton extends UIElement {
	String clickAction;
	Sprite normal;
	Sprite hover;
	Sprite clicked;
	boolean click;

	public UIButton(int x, int y, String clickAction, Sprite sprite, Sprite hover, Sprite clicked) {
		super(x, y, sprite);
		this.clickAction = clickAction;
		click = false;
		this.normal = sprite;
		this.hover = hover;
		this.clicked = clicked;
	}

	public void update(int mouseX, int mouseY, boolean buttonDown) {
		if (updHitShape != null) {
			boolean inside = updHitShape.collision(new HitShape(new HitPoint(new VectorD(new double[] { mouseX, mouseY ,0}))));
			if (inside && buttonDown){
				click = true;	
				sprite = clicked;
			}
			else if (inside && click){
				try {
					Method m = ui.getController().getClass().getMethod(clickAction, null);
					m.invoke(ui, null);
					click = false;
					sprite = normal;
				}
				catch(Exception e){
					e.printStackTrace();
				}				
			}
			else if (inside){
				sprite = hover;
				click = false;
			}
			else {
				click = false;
				sprite = normal;
			}
		}
	}
	
	public void highlight(){
		System.out.println("hover");
	}

}
