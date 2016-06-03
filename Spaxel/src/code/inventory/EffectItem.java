package code.inventory;

import code.Game;
import code.engine.Engine;
import code.engine.EntityType;
import code.entity.Entity;
import code.graphics.RenderBuffer;
import code.graphics.Sprite;
import code.input.Mouse;


public class EffectItem extends Item{
	private Sprite effectSprite;

	public EffectItem(EntityType type, Sprite sprite, Sprite bar, int cooldown, Sprite effectSprite) {
		super(type, sprite, bar, cooldown);
		this.effectSprite = effectSprite;
	}

	public void render(int xPos, int yPos, RenderBuffer render){
		sprite.render(xPos, yPos, render);
		cooldownBar.render(xPos - 24, yPos,render);
		if (canUpdate()){
			Mouse mouse = Engine.getEngine().getMouse();
			int xPlayer = 3*Game.game.GAME_WIDTH/4 - 8*4 - mouse.getX()/2;
			int yPlayer = 3*Game.game.GAME_HEIGHT/4 - 8*4 - mouse.getY()/2;
			effectSprite.render(xPlayer, yPlayer, render, .75);
		}
	}

	public boolean canUpdate(){
		return cd <= 0;
	}
	

}
