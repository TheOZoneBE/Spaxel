package code.system;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import code.Game;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.SystemType;
import code.entity.Entity;
import code.entity.Player;
import code.graphics.RenderBuffer;
import code.input.Mouse;
import code.inventory.Item;

public class RenderSystem extends GameSystem {
	private RenderBuffer mainBuffer;
	private RenderBuffer UIBuffer; //mouse1item, mouse3item, shipitem, uielement
	private int xOffset;
	private int yOffset;
	private ExecutorService exs;

	public RenderSystem() {
		super(SystemType.RENDER);
		mainBuffer = new RenderBuffer();
		UIBuffer = new RenderBuffer();
		exs = Executors.newCachedThreadPool();
	}

	public void update(Graphics g){
		Mouse mouse = Engine.getEngine().getMouse();
		mainBuffer.clear();
		CountDownLatch latch = new CountDownLatch(1);
		if (Engine.getEngine().getGameState() != Engine.GameState.MENU) {
			Entity player = Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
			int screenXOffset = mouse.getX() / 2 - Game.GAME_WIDTH / 4;
			int screenYOffset = mouse.getY() / 2 - Game.GAME_HEIGHT / 4;
			int playerXPos = Game.GAME_WIDTH / 2 - 8 * 4 - screenXOffset;
			int playerYPos = Game.GAME_HEIGHT / 2 - 8 * 4 - screenYOffset;
			xOffset = playerXPos - (int) player.getX();
			yOffset = playerYPos - (int) player.getY();
			//mainBuffer.dots(xOffset, yOffset);

			latch = new CountDownLatch(2);
			exs.execute(new mainRender(latch));
		}
		exs.execute(new UIRender(g, latch));
		try{
			latch.await();
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
	}

	public class mainRender implements Runnable {
		private CountDownLatch latch;

		public mainRender(CountDownLatch latch){
			this.latch = latch;
		}

		@Override
		public void run() {

			Iterator<Entity> toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.TRAILSEGMENT);
			while(toRender.hasNext()){
				Entity e = toRender.next();
				e.render(xOffset, yOffset, mainBuffer);
			}
			toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.PARTICLE);
			while(toRender.hasNext()){
				Entity e = toRender.next();
				e.render(xOffset, yOffset, mainBuffer);
			}

			toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.DROPPEDITEM);
			while(toRender.hasNext()){
				Entity e = toRender.next();
				e.render(xOffset, yOffset, mainBuffer);
			}
			toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.PLAYER_PROJECTILE);
			while(toRender.hasNext()){
				Entity e = toRender.next();
				e.render(xOffset, yOffset, mainBuffer);
			}

			toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.ENEMY_PROJECTILE);
			while(toRender.hasNext()){
				Entity e = toRender.next();
				e.render(xOffset, yOffset, mainBuffer);
			}
			Entity player = Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
			int playerXPos = xOffset + (int)player.getX();
			int playerYPos = yOffset + (int)player.getY();
			player.render(playerXPos,playerYPos, mainBuffer);

			toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.ENEMY);
			while(toRender.hasNext()){
				Entity e = toRender.next();
				e.render(xOffset, yOffset, mainBuffer);
			}
			//Engine.getEngine().temp.render(xOffset, yOffset, mainBuffer);
			latch.countDown();

		}

	}

	public class UIRender implements Runnable {
		private CountDownLatch latch;
		Graphics graphics;
		public UIRender(Graphics g, CountDownLatch latch){
			this.latch = latch;
			graphics = g;
		}

		@Override
		public void run() {
			UIBuffer.clear();
			UISystem uis = (UISystem)Engine.getEngine().getSystem(SystemType.UI);
			uis.getCurrentUI().render(graphics, UIBuffer);
			latch.countDown();
		}
	}

	public class RenderBatch implements Runnable {
		private int x;
		private int y;
		private int w;
		private int h;
		private CountDownLatch latch;
		public RenderBatch(int x, int y, int w, int h, CountDownLatch latch){
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			this.latch = latch;
		}

		@Override
		public void run() {
			for (int i = 0; i < w; i++){
				for (int j = 0; j < h; j++){
					/*
					//TODO use opengl rendering here
					if (Engine.getEngine().getGameState() != Engine.GameState.MENU){
						if (UIBuffer.getPixel(x+i, y+j) != 0){
							Game.game.pixels[x +i + (y+j)* Game.GAME_WIDTH] = UIBuffer.getPixel(x +i, y +j);
						}
						else {
							Game.game.pixels[x +i + (y+j)* Game.GAME_WIDTH] = mainBuffer.getPixel(x +i, y +j);
						}
					}
					else {
						Game.game.pixels[x +i + (y+j)* Game.GAME_WIDTH] = UIBuffer.getPixel(x +i, y +j);
					}*/

				}
			}
			latch.countDown();
		}
	}

	public void render(Graphics g){
		update(g);
		int stepsize = 16;
		CountDownLatch latch = new CountDownLatch(stepsize*stepsize);
		int w = Game.GAME_WIDTH /stepsize;
		int h = Game.GAME_HEIGHT /stepsize;
		for (int i = 0; i < stepsize; i++){
			for (int j = 0; j < stepsize; j++){
				exs.execute(new RenderBatch(i*w, j*h, w, h, latch));
			}
		}
		try{
			latch.await();
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}
