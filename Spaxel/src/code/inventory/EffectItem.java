package code.inventory;

import code.Game;
import code.engine.Engine;
import code.engine.EntityType;
import code.entity.Entity;
import code.graphics.RenderBuffer;
import code.graphics.Sprite;
import code.input.Mouse;

import java.awt.*;


public class EffectItem extends Item{
	protected Sprite effectSprite;

	public EffectItem(EntityType type,String name,  Sprite sprite, Sprite bar, int cooldown, Sprite effectSprite) {
		super(type,name,  sprite, bar, cooldown);
		this.effectSprite = effectSprite;
	}

	public void render(int xPos, int yPos, Graphics g, RenderBuffer render){
		super.render(xPos, yPos, g, render);
		cooldownBar.render(xPos - 24, yPos,render);
		if (canUpdate()){
			Mouse mouse = Engine.getEngine().getMouse();
			int xPlayer = 3* Game.GAME_WIDTH /4 - 8*4 - mouse.getX()/2;
			int yPlayer = 3* Game.GAME_HEIGHT /4 - 8*4 - mouse.getY()/2;
			effectSprite.render(xPlayer, yPlayer, render, .75f);
		}
	}

	public boolean canUpdate(){
		return cd <= 0;
	}
	

}
