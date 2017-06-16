package code.entity;

import code.engine.Engine;
import code.engine.SystemType;
import code.graphics.MasterBuffer;
import code.graphics.RenderBuffer;
import code.graphics.SpriteData;
import code.input.Keyboard;
import code.system.UISystem;
import code.ui.Label;
import code.ui.UIBar;
import com.sun.xml.internal.bind.v2.TODO;

public class Player extends Actor {
	private int mouseX;
	private int mouseY;
	private int xpToLevel;
	private int xp;
	private int level;

	public Player(float x, float y, float rot, int health, SpriteData sprite, float maxspeed, float turnrate, float acc) {
		super(x, y, rot, health, sprite, maxspeed,turnrate, acc);
		xpToLevel = 100;
		level = 1;
	}

	public void update(Keyboard keys, int mouseX, int mouseY) {		
		float dx = -xdir/(maxspeed*2);
		float dy = -ydir/(maxspeed*2);
		if (keys.downState.getState()) {
			dx = (float)-Math.sin(rot) * acc;
			dy = (float)-Math.cos(rot) * acc;
		}
		if (keys.upState.getState()) {
			dx = (float)Math.sin(rot) * acc;
			dy = (float)Math.cos(rot) * acc;
		}
		if (keys.leftState.getState()) {
			dx = (float)Math.sin(rot + Math.PI / 2) * acc;
			dy = (float)Math.cos(rot + Math.PI / 2) * acc;
		}

		if (keys.rightState.getState()) {
			dx = (float)Math.sin(rot - Math.PI / 2) * acc;
			dy = (float)Math.cos(rot - Math.PI / 2) * acc;
		}
		if (controlSpeed(xdir + dx, ydir + dy)) {
			xdir += dx;
			ydir += dy;
		} else {
			xdir = xdir - xdir/(maxspeed*2);
			ydir = ydir - ydir/(maxspeed*2);
		}

		x+= xdir*Engine.getEngine().getUpdateTime();
		y+=ydir*Engine.getEngine().getUpdateTime();
		
		this.mouseX = mouseX;
		this.mouseY = mouseY;

		if (health <= 0){
			Engine.getEngine().stopGame();
			UISystem uis = (UISystem) Engine.getEngine().getSystem(SystemType.UI);
			uis.changeUI("game_over");
			Engine.getEngine().setGameState(Engine.GameState.MENU);
		}

		if (xp >= xpToLevel){
			xp-= xpToLevel;
			xpToLevel*=1.5;
			maxHealth*=1.5;
			health = maxHealth;
		}

		UIBar hp_bar = (UIBar) Engine.getEngine().getUIAtlas().get("play").getElement("hp_bar");
		hp_bar.setPercent(health/(float)maxHealth);
		UIBar xp_bar = (UIBar) Engine.getEngine().getUIAtlas().get("play").getElement("xp_bar");
		xp_bar.setPercent(xp/(float)xpToLevel);

		Label hp_label = (Label)Engine.getEngine().getUIAtlas().get("play").getElement("hp_label");
		Label xp_label = (Label)Engine.getEngine().getUIAtlas().get("play").getElement("xp_label");
		hp_label.setText(health + " / " + maxHealth);
		xp_label.setText(xp + " / " + xpToLevel);


		super.update();
	}

	public int getXp(){
		return xp;
	}

	public void setXp(int xp){
		this.xp = xp;
	}

	public int getXpToLevel(){
		return xpToLevel;
	}

	public void setXpToLevel(int xpToLevel){
		this.xpToLevel = xpToLevel;
	}

	public void render(int xPos, int yPos, MasterBuffer render) {
		rot = (float)(Math.atan2(((float) (mouseX - xPos)), (float) (mouseY - yPos)));
		//TODO sprite.renderBlur(xPos, yPos, rot, render, .5f);
		sprite.renderSprite(xPos, yPos,4, rot, 1, false, render);
	}

}
