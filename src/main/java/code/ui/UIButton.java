package code.ui;

import java.lang.reflect.Method;

import code.collision.HitPoint;
import code.collision.HitShape;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.graphics.MasterBuffer;
import code.graphics.RenderData;
import code.graphics.RenderLayer;
import code.input.MouseWrapper;
import code.math.MatrixF;
import code.math.MatrixMaker;
import code.math.VectorF;
import com.fasterxml.jackson.annotation.JsonSetter;

public class UIButton extends UIVisual {
	protected String onClick;
	protected HitShape hitShape;
	protected SpriteComponent hover;
	protected SpriteComponent clicked;
	protected SpriteComponent locked;

	protected boolean click;
	protected boolean disabled;
	protected boolean hovering;

	public void update() {
		if (!disabled) {
			MouseWrapper mouseWrapper = Engine.getEngine().getMouseWrapper();
			int mouseX = mouseWrapper.getX();
			int mouseY = mouseWrapper.getY();
			boolean buttonDown = mouseWrapper.mouse1;
			MatrixF transform = MatrixMaker.getTransformationMatrix(position.getCoord(), position.getRot(), 1, 1);
			HitShape updated = hitShape.update(transform);
			boolean inside = updated
					.collision(new HitShape(new HitPoint(new VectorF(new float[] { mouseX, mouseY, 0 }))));
			// TODO revisit
			if (inside && buttonDown) {
				click = true;
			} else if (inside && click) {
				try {
					Method m = controller.getClass().getMethod(onClick);
					m.invoke(controller);
					click = false;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (inside) {
				hovering = true;
				click = false;
			} else {
				hovering = false;
				click = false;
			}
		}
		for (UIElement child : children) {
			child.update();
		}
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public void render(MasterBuffer buffer) {
		SpriteComponent toRender;
		if (click) {
			toRender = clicked;
		} else if (hovering) {
			toRender = hover;
		} else if (disabled) {
			toRender = locked;
		} else {
			toRender = sprite;
		}

		RenderData data = new RenderData();
		data.setPos(position.getCoord());
		data.setRot(position.getRot());
		data.setXScale(toRender.getScale() * toRender.getSprite().getWidth());
		data.setYScale(toRender.getScale() * toRender.getSprite().getHeight());
		data.setSpriteSheetID(toRender.getSprite().getSpritesheetID());
		data.setTexOffset(toRender.getSprite().getSpriteProperties());
		buffer.addNewSprite(RenderLayer.UI, data);

		for (UIElement child : children) {
			child.render(buffer);
		}
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public HitShape getHitShape() {
		return hitShape;
	}

	public void setHitShape(HitShape hitShape) {
		this.hitShape = hitShape;
	}

	@JsonSetter("hitShape")
	public void setHitShape(String hitShapeName) {
		this.hitShape = Engine.getEngine().getHitShapeAtlas().get(hitShapeName);
	}

	public SpriteComponent getHover() {
		return hover;
	}

	public void setHover(SpriteComponent hover) {
		this.hover = hover;
	}

	public SpriteComponent getClicked() {
		return clicked;
	}

	public void setClicked(SpriteComponent clicked) {
		this.clicked = clicked;
	}

	public SpriteComponent getLocked() {
		return locked;
	}

	public void setLocked(SpriteComponent locked) {
		this.locked = locked;
	}

	public boolean isClick() {
		return click;
	}

	public void setClick(boolean click) {
		this.click = click;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
}
