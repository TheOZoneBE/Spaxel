package code.ui;

import java.awt.*;
import java.lang.reflect.Method;

import code.collision.HitPoint;
import code.collision.HitShape;
import code.engine.Engine;
import code.graphics.RenderBuffer;
import code.graphics.Sprite;
import code.input.Mouse;
import code.math.VectorD;

public class UIButton extends UIElement {
	private String clickAction;
	private Label label;
	private Sprite normal;
	private Sprite hover;
	private Sprite clicked;
	private Sprite locked;
	private boolean click;
	private boolean disabled;

	public UIButton(int x, int y, Label label, String clickAction, Sprite sprite, Sprite hover, Sprite clicked, Sprite locked)  {
		super(x, y, sprite);
		this.label = label;
		this.clickAction = clickAction;
		click = false;
		this.normal = sprite;
		this.hover = hover;
		this.clicked = clicked;
		this.locked = locked;
		this.disabled = false;
	}

	public void update() {
		if (disabled){
			sprite = locked;
		}
		else {
			Mouse mouse = Engine.getEngine().getMouse();
			int mouseX = mouse.getX();
			int mouseY = mouse.getY();
			boolean buttonDown = mouse.mouse1;
			if (updHitShape != null) {
				boolean inside = updHitShape.collision(new HitShape(new HitPoint(new VectorD(new double[] { mouseX, mouseY ,0}))));
				if (inside && buttonDown){
					click = true;
					sprite = clicked;
				}
				else if (inside && click){
					try {
						Method m = ui.getController().getClass().getMethod(clickAction, null);
						m.invoke(ui.getController(), null);
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
	}

	public void setDisabled(boolean disabled){
		this.disabled = disabled;
	}

	public void render(Graphics g, RenderBuffer render){
		sprite.render((int)x,(int)y, render);
		label.render(g, render);
	}

}
