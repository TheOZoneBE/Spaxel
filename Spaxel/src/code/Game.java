package code;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import code.graphics.Render;
import code.input.Keyboard;
import code.input.Mouse;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public final int GAME_HEIGHT = 720;
	public final int GAME_WIDTH = 1280;
	public static Game game;
	public boolean running = false;

	private Thread thread;
	private JFrame frame;
	private long time;
	private Render render;
	private BufferedImage image = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private Keyboard keyboard;
	private Mouse mouse;

	public static void main(String[] args) {
		game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Spaxel - Devbuild 0.1.0");
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
		time = System.nanoTime();
		render = new Render(GAME_WIDTH, GAME_HEIGHT);
		keyboard = new Keyboard();
		addKeyListener(keyboard);
		mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
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
		while (running) {
			long deltaTime = System.nanoTime() - time;
			if (deltaTime > 20000000) {
				update();
				time = System.nanoTime();
			}
			render();
		}
	}

	public void update() {
		requestFocus();
		keyboard.update();
	}

	public void render() {
		render.render();
		BufferStrategy bs = getBufferStrategy();
		for (int i = 0; i < GAME_WIDTH * GAME_HEIGHT; i++) {
			pixels[i] = render.getPixel(i);
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

}
