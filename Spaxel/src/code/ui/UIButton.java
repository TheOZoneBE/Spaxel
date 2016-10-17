package code.ui;

import java.lang.reflect.Method;

import code.collision.HitPoint;
import code.collision.HitShape;
import code.engine.Engine;
import code.graphics.MasterBuffer;
import code.graphics.RenderBuffer;
import code.graphics.SpriteData;
import code.input.MouseWrapper;
import code.math.VectorF;

public class UIButton extends UIElement {
	private String clickAction;
	private Label label;
	private SpriteData normal;
	private SpriteData hover;
	private SpriteData clicked;
	private SpriteData locked;
	private boolean click;
	private boolean disabled;
	private boolean hovering;

	public UIButton(int x, int y, Label label, String clickAction, SpriteData sprite, SpriteData hover, SpriteData clicked, SpriteData locked)  {
		super(x, y, sprite);
		this.label = label;
		this.clickAction = clickAction;
		click = false;
		this.normal = sprite;
		this.hover = hover;
		this.clicked = clicked;
		this.locked = locked;
		this.disabled = false;
		this.hovering = false;
	}

	public void update() {
		if (disabled){
			sprite = locked;
		}
		else {
			MouseWrapper mouseWrapper = Engine.getEngine().getMouseWrapper();
			int mouseX = mouseWrapper.getX();
			int mouseY = mouseWrapper.getY();
			boolean buttonDown = mouseWrapper.mouse1;
			boolean inside = updHitShape.collision(new HitShape(new HitPoint(new VectorF(new float[] { mouseX, mouseY ,0}))));
			if (inside && buttonDown){
				click = true;
			}
			else if (inside && click){
				try {
					Method m = ui.getController().getClass().getMethod(clickAction, null);
					m.invoke(ui.getController(), null);
					click = false;
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			else if (inside){
				hovering = true;
				click = false;
			}
			else {
				hovering = false;
				click = false;
			}
		}

	}

	public void setDisabled(boolean disabled){
		this.disabled = disabled;
	}

	public void render(MasterBuffer render){
		sprite.renderSprite((int)x,(int)y,4, 0, 1, false ,render);
		if (click){
			clicked.renderSprite((int)x,(int)y,4,0,1, false, render);
		}
		else if (hovering){
			hover.renderSprite((int)x,(int)y,4, 0,1,false, render);
		}
		label.render(render);
	}

}
