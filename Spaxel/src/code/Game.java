package code;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import code.level.Level;
import code.level.PlayerSystem;
import code.sound.SoundSystem;
import code.system.AISystem;
import code.system.ParticleSystem;
import code.system.ProjectileSystem;
import code.ui.MainUI;
import code.ui.UI;
import code.ui.UIButton;
import code.ui.UISystem;
import code.collision.HitPoint;
import code.collision.HitShape;
import code.engine.Engine;
import code.engine.EntityType;
import code.entity.Enemy;
import code.entity.Player;
import code.graphics.RenderBuffer;
import code.graphics.RenderSystem;
import code.graphics.Sprite;
import code.graphics.Spritesheet;
import code.input.Keyboard;
import code.input.Mouse;
import code.inventory.InventorySystem;
import code.math.VectorD;

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
	
	private Keyboard keyboard;//done
	private Mouse mouse;//done
	private Player player;//add to entitystream
	private HitShape hitShape;//move to engine
	private HitPoint hitPoint;//move to engine
	private Spritesheet sheet;//move to engine
	private Sprite sprite;//move to engine
	//private UI ui;//convert
	//private UIButton uiE;//add to entitystream

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
		
		//todo change this to loaders
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
		
		engine.getEntityStream().addEntity(EntityType.PLAYER, player);
		Enemy temp = new Enemy(128,128,.45,sprite);
		temp.setHitShape(hitShape);
		engine.getEntityStream().addEntity(EntityType.ENEMY, temp);
		
		//ui = new MainUI(sprite);
		//uiE = new UIButton(512, 64, "startGame", sprite);
		//uiE.setHitShape(hitShape);
		//ui.addElement(uiE);
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
		//ui.update(mouse);
	}

	public void render() {
		engine.render();
		//this also
		//ui.render(render);
		BufferStrategy bs = getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

}
