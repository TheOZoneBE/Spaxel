package code.inventory;

import code.Game;
import code.engine.Engine;
import code.engine.EntityType;
import code.graphics.MasterBuffer;
import code.graphics.RenderBuffer;
import code.graphics.SpriteData;
import code.input.MouseWrapper;


public class EffectItem extends Item{
	protected SpriteData effectSprite;

	public EffectItem(EntityType type, String name, SpriteData sprite, SpriteData bar, int cooldown, SpriteData effectSprite) {
		super(type,name,  sprite, bar, cooldown);
		this.effectSprite = effectSprite;
	}

	public void render(int xPos, int yPos, MasterBuffer render){
		super.render(xPos, yPos, render);
		cooldownBar.render(xPos - 24, yPos,render);
		if (canUpdate()){
			MouseWrapper mouseWrapper = Engine.getEngine().getMouseWrapper();
			int xPlayer = 3* Game.GAME_WIDTH /4 - 8*4 - mouseWrapper.getX()/2;
			int yPlayer = 3* Game.GAME_HEIGHT /4 - 8*4 - mouseWrapper.getY()/2;
			effectSprite.renderSprite(xPlayer, yPlayer, 4, 0, .25f, false, render);
		}
	}

	public boolean canUpdate(){
		return cd <= 0;
	}
	

}
