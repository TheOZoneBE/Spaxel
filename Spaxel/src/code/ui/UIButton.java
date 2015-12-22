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
	HitShape box;
	Sprite sprite;
	boolean click;

	public UIButton(int x, int y, String clickAction, Sprite sprite) {
		super(x, y);
		this.sprite = sprite;
		this.clickAction = clickAction;
		click = false;
	}

	public void setHitShape(HitShape box) {
		this.box = box.update(MatrixMaker.getTransRotMatrix(x, y, 0));
	}

	public void update(int mouseX, int mouseY, boolean clicked) {
		if (box != null) {
			boolean inside = box.collision(new HitShape(new HitPoint(new VectorD(new double[] { mouseX, mouseY ,0}))));
			if (inside && clicked){
				click = true;				
			}
			else if (inside && click){
				try {
					Method m = ui.getClass().getMethod(clickAction, null);
					m.invoke(ui, null);
					click = false;
				}
				catch(Exception e){
					e.printStackTrace();
				}				
			}
			else if (inside){
				highlight();
				click = false;
			}
			else {
				click = false;
			}
		}
	}

	public void render(RenderBuffer render) {
		if (box != null) {
			box.render(0, 0, render);
		}
		sprite.render((int)x,(int)y, render);
	}
	
	public void highlight(){
		System.out.println("hover");
	}

}
