package code;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;

import javax.swing.JFrame;

import code.engine.LoadingScreen;
import code.engine.SystemUpdater;
import code.engine.Engine;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public final static int GAME_HEIGHT = 720;
	public final static int GAME_WIDTH = 1280;
	public static Game game;
	public boolean running = false;
	private String gameName = "Spaxel - Devbuild 0.2.1";

	private Thread thread;
	private JFrame frame;
	private BufferedImage image = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage textBuffer = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
	public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] textReset = ((DataBufferInt) textBuffer.getRaster().getDataBuffer()).getData();

	public LoadingScreen loadingScreen;
	public SystemUpdater updater;
	
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
		loadingScreen = new LoadingScreen();
		updater = new SystemUpdater();
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
		long accTime = 20000000;
		while (running) {
			long start = System.nanoTime();
			if (accTime >= 20000000) {
				accTime -= 20000000;
				if (!Engine.getEngine().isLoading()) {
					update();
				}
				ups++;
			}
			if (fps < ups){
				if (Engine.getEngine().isLoading()) {
					renderLoading();
				} else {
					render();
				}
				fps++;
			}

			if (ups == 50) {
				frame.setTitle(gameName + " @ " + fps + " fps");
				ups = 0;
				fps = 0;
			}
			accTime += System.nanoTime() - start;
		}
	}

	public void update() {
		//Engine.getEngine().update();
		updater.update();
	}

	public void render() {
		clearText();
		BufferStrategy bs = getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		Graphics g2 = textBuffer.getGraphics();
		updater.render(g2);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(textBuffer, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public void renderLoading(){
		clearText();
		BufferStrategy bs = getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		Graphics g2 = textBuffer.getGraphics();
		loadingScreen.render(g2);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(textBuffer, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public void clearText(){
		for (int i = 0; i < GAME_WIDTH*GAME_HEIGHT; i++){
			textReset[i] = 0;
		}
	}

}
