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
import code.graphics.RenderBuffer;
import code.input.Mouse;

public class RenderSystem extends GameSystem {
	private RenderBuffer mainBuffer;
	private RenderBuffer particleBuffer; //particle, trailsegment, droppeditem
	private RenderBuffer projectileBuffer; //enemy_projectile, player_projectile
	private RenderBuffer actorBuffer; //player, enemy
	private RenderBuffer UIBuffer; //mouse1item, mouse3item, shipitem, uielement
	private RenderBuffer[] buffers;
	private int xOffset;
	private int yOffset;
	private ExecutorService exs;
	private int executing = 0;

	public RenderSystem() {
		super(SystemType.RENDER);
		mainBuffer = new RenderBuffer(Game.GAME_WIDTH, Game.GAME_HEIGHT);
		particleBuffer = new RenderBuffer(Game.GAME_WIDTH, Game.GAME_HEIGHT);
		projectileBuffer = new RenderBuffer(Game.GAME_WIDTH, Game.GAME_HEIGHT);
		actorBuffer = new RenderBuffer(Game.GAME_WIDTH, Game.GAME_HEIGHT);
		UIBuffer = new RenderBuffer(Game.GAME_WIDTH, Game.GAME_HEIGHT);
		buffers = new RenderBuffer[5];
		buffers[0] = mainBuffer;
		buffers[1] = particleBuffer;
		buffers[2] = projectileBuffer;
		buffers[3] = actorBuffer;
		buffers[4] = UIBuffer;
		exs = Executors.newCachedThreadPool();
	}

	public void update(){
		Mouse mouse = Engine.getEngine().getMouse();
		mainBuffer.clear();
		if (Engine.getEngine().getGameState() != Engine.GameState.MENU) {
			Entity player = Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
			int screenXOffset = mouse.getX() / 2 - Game.GAME_WIDTH / 4;
			int screenYOffset = mouse.getY() / 2 - Game.GAME_HEIGHT / 4;
			int playerXPos = Game.GAME_WIDTH / 2 - 8 * 4 - screenXOffset;
			int playerYPos = Game.GAME_HEIGHT / 2 - 8 * 4 - screenYOffset;
			xOffset = playerXPos - (int) player.getX();
			yOffset = playerYPos - (int) player.getY();
			mainBuffer.dots(xOffset, yOffset);

			executing++;
			exs.execute(new particleRender());
			executing++;
			exs.execute(new projectileRender());
			executing++;
			exs.execute(new actorRender());
		}
		executing++;
		exs.execute(new UIRender());
		synchronized (this){
			try {
				while(executing != 0){
					this.wait();
				}
				render();
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	public void renderedPart(){
		synchronized (this){
			executing--;
			this.notifyAll();
		}
	}

	public class particleRender implements Runnable {
		@Override
		public void run() {
			particleBuffer.clear();
			Iterator<Entity> toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.TRAILSEGMENT);
			while(toRender.hasNext()){
				Entity e = toRender.next();
				e.render(xOffset, yOffset, particleBuffer);
			}
			toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.PARTICLE);
			while(toRender.hasNext()){
				Entity e = toRender.next();
				e.render(xOffset, yOffset, particleBuffer);
			}

			toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.DROPPEDITEM);
			while(toRender.hasNext()){
				Entity e = toRender.next();
				e.render(xOffset, yOffset, particleBuffer);
			}
			renderedPart();
		}
	}

	public class projectileRender implements Runnable {
		@Override
		public void run() {
			projectileBuffer.clear();
			Iterator<Entity> toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.PLAYER_PROJECTILE);
			while(toRender.hasNext()){
				Entity e = toRender.next();
				e.render(xOffset, yOffset, projectileBuffer);
			}

			toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.ENEMY_PROJECTILE);
			while(toRender.hasNext()){
				Entity e = toRender.next();
				e.render(xOffset, yOffset, projectileBuffer);
			}
			renderedPart();
		}
	}

	public class actorRender implements Runnable {
		@Override
		public void run() {
			actorBuffer.clear();
			Entity player = Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
			int playerXPos = xOffset + (int)player.getX();
			int playerYPos = yOffset + (int)player.getY();
			player.render(playerXPos,playerYPos, actorBuffer);

			Iterator<Entity> toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.ENEMY);
			while(toRender.hasNext()){
				Entity e = toRender.next();
				e.render(xOffset, yOffset, actorBuffer);
			}

			toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.SHIPITEM);
			int i= 0;
			while (toRender.hasNext()){
				Entity e = toRender.next();
				e.render(386 + i * 72, 680, actorBuffer);
				i++;
			}

			renderedPart();
		}
	}

	public class UIRender implements Runnable {
		@Override
		public void run() {
			UIBuffer.clear();
			Iterator<Entity> toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.MOUSE1ITEM);
			int i = 0;
			while (toRender.hasNext()){
				Entity e = toRender.next();
				e.render(40, 40 + i * 72, UIBuffer);
				i++;
			}

			toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.MOUSE3ITEM);
			i= 0;
			while (toRender.hasNext()){
				Entity e = toRender.next();
				e.render(1240, 40 + i * 72, UIBuffer);
				i++;
			}

			UISystem uis = (UISystem)Engine.getEngine().getSystem(SystemType.UI);
			uis.getCurrentUI().render(UIBuffer);
			renderedPart();
		}
	}

	public class RenderBatch implements Runnable {
		private int x;
		private int y;
		private CountDownLatch latch;
		public RenderBatch(int x, int y, CountDownLatch latch){
			this.x = x;
			this.y = y;
			this.latch = latch;
		}

		@Override
		public void run() {
			for (int i = 0; i < 160; i++){
				for (int j = 0; j < 90; j++){
					int k = 4;
					while (k != 0 && buffers[k].getPixel(x + i, y + j) == 0){
						k--;
					}
					Game.game.pixels[x +i + (y+j)*Game.game.GAME_WIDTH] = buffers[k].getPixel(x +i, y +j);
				}
			}
			latch.countDown();
		}
	}

	public void render(){
		CountDownLatch latch = new CountDownLatch(8*8);
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				exs.execute(new RenderBatch(i*160, j*90, latch));
			}
		}
		try{
			latch.await();
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void drawText(Graphics g){
		UISystem uis = (UISystem)Engine.getEngine().getSystem(SystemType.UI);
		uis.getCurrentUI().drawText(g);
	}

}
