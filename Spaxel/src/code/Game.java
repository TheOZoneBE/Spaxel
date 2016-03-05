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

import code.level.PlayerSystem;
import code.sound.SoundSystem;
import code.system.AISystem;
import code.system.ParticleSystem;
import code.system.ProjectileSystem;
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
	
	private Engine engine;
	
	private Keyboard keyboard;
	private Mouse mouse;

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
		keyboard = new Keyboard();
		mouse = new Mouse();
		addKeyListener(keyboard);		
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		engine = new Engine(keyboard, mouse);
		engine.addSystem(new SoundSystem(engine));
		engine.addSystem(new InventorySystem(engine));
		engine.addSystem(new UISystem(engine));
		engine.addSystem(new ProjectileSystem(engine));
		engine.addSystem(new PlayerSystem(engine));
		engine.addSystem(new RenderSystem(engine));
		engine.addSystem(new AISystem(engine));
		engine.addSystem(new ParticleSystem(engine));		
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
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
				update();				
				ups++;
			}
			render();
			fps++;
			if (ups == 50) {
				frame.setTitle(gameName + " @ " + fps + " fps");
				ups = 0;
				fps = 0;
			}
		}
	}

	public void update() {
		engine.update();
		keyboard.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		engine.render();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		engine.drawText(g);
		/*
		 * proof of concept
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/ARCADECLASSIC.TTF"));
			font = font.deriveFont(20f);
			g.setFont(font);
		}
		catch (Exception e){
			
		}
		g.drawString("PLAY", 320, 320);*/
		g.dispose();
		bs.show();
	}

}
