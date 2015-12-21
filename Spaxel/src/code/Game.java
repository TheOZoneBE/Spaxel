package code;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import code.level.Level;
import code.sound.MusicPlayer;
import code.ui.MainUI;
import code.ui.UI;
import code.ui.UIButton;
import code.collision.HitPoint;
import code.collision.HitShape;
import code.engine.Engine;
import code.entity.Player;
import code.graphics.Render;
import code.graphics.Sprite;
import code.graphics.Spritesheet;
import code.input.Keyboard;
import code.input.Mouse;
import code.math.VectorD;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public final static int GAME_HEIGHT = 720;
	public final static int GAME_WIDTH = 1280;
	public static Game game;
	public boolean running = false;
	private String gameName = "Spaxel - Devbuild 0.1.4";

	private Thread thread;
	private JFrame frame;
	private long time;
	private BufferedImage image = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	private Engine engine;
	
	private Render render;//convert
	private Keyboard keyboard;//done
	private Mouse mouse;//done
	private Player player;//add to entitystream
	private HitShape hitShape;//move to engine
	private HitPoint hitPoint;//move to engine
	private Level level;//convert
	private Spritesheet sheet;//move to engine
	private Sprite sprite;//move to engine
	private MusicPlayer music;//convert
	private UI ui;//convert
	private UIButton uiE;//add to entitystream

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
		render = new Render(GAME_WIDTH, GAME_HEIGHT);
		keyboard = new Keyboard();
		addKeyListener(keyboard);
		mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		sheet = new Spritesheet(32, 32, "/spritesheets/ships.png");
		sprite = new Sprite(16, 16, 0, 0, 4, sheet);
		player = new Player(0, 0, 0, sprite);
		hitShape = new HitShape();
		hitPoint = new HitPoint(new VectorD(new double[] { 64, 64, 1 }));
		hitShape.addHitPoint(hitPoint);
		hitPoint = new HitPoint(new VectorD(new double[] {64, -64, 1}));
		hitShape.addHitPoint(hitPoint);
		hitPoint = new HitPoint(new VectorD(new double[] {-64, -64, 1}));
		hitShape.addHitPoint(hitPoint);
		hitPoint = new HitPoint(new VectorD(new double[] {-64, 64, 1}));
		hitShape.addHitPoint(hitPoint);
		player.setHitShape(hitShape);
		level = new Level();
		level.addPlayer(player);
		music = new MusicPlayer();
		ui = new MainUI(sprite);
		uiE = new UIButton(512, 64, "startGame", sprite);
		uiE.setHitShape(hitShape);
		ui.addElement(uiE);
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		music.stop();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		int ups = 0;
		int fps = 0;
		music.play();
		while (running) {
			long deltaTime = System.nanoTime() - time;
			if (deltaTime > 20000000) {
				update();
				time = System.nanoTime();
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
		keyboard.update();
		level.update(keyboard, mouse);
		music.update();
		ui.update(mouse);
	}

	public void render() {
		render.render(0,0);
		level.render(render);
		ui.render(render);
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
