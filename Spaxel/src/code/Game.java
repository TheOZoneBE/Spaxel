package code;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.InputStream;

import javax.swing.JFrame;

import code.engine.LoadingScreen;
import code.level.PlayerSystem;
import code.sound.SoundSystem;
import code.system.AISystem;
import code.system.ParticleSystem;
import code.system.ProjectileSystem;
import code.system.TrailSystem;
import code.ui.UISystem;
import code.engine.Engine;
import code.graphics.RenderSystem;
import code.input.Keyboard;
import code.input.Mouse;
import code.inventory.InventorySystem;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public final static int GAME_HEIGHT = 720;
	public final static int GAME_WIDTH = 1280;
	public static Game game;
	public boolean running = false;
	private String gameName = "Spaxel - Devbuild 0.2.1";

	private Thread thread;
	private JFrame frame;
	private long time;
	private BufferedImage image = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
	public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public LoadingScreen loadingScreen;
	
	//private Engine engine;

	public static void main(String[] args) {
		game = new Game();
		game.frame.setResizable(false);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.createBufferStrategy(3);
		game.start();
	}

	public Game() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setPreferredSize(size);
		frame = new JFrame();
		frame.setTitle(gameName);
		time = System.nanoTime();
		loadingScreen = new LoadingScreen();
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
		Engine.getEngine().initialize();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		requestFocus();
		int ups = 0;
		int fps = 0;
		while (running) {
			long deltaTime = System.nanoTime() - time;
			if (deltaTime > 20000000) {
				time = System.nanoTime();
				if (Engine.getEngine().isLoading()){
					loadingScreen.update();
				}
				else {
					update();
				}
				ups++;
			}
			if (Engine.getEngine().isLoading()){
				renderLoading();
			}
			else {
				render();
			}
			fps++;
			if (ups == 50) {
				frame.setTitle(gameName + " @ " + fps + " fps");
				ups = 0;
				fps = 0;
			}
		}
	}

	public void update() {
		Engine.getEngine().update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		Engine.getEngine().render();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		Engine.getEngine().drawText(g);
		g.dispose();
		bs.show();
	}

	public void renderLoading(){
		BufferStrategy bs = getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		loadingScreen.render();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		loadingScreen.drawText(g);
		g.dispose();
		bs.show();
	}

}
