package code;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import code.engine.Engine;
import code.engine.LoadingScreen;
import code.engine.SystemUpdater;
import code.graphics.SpriteData;
import code.graphics.Spritesheet;
import code.math.MatrixF;
import code.math.MatrixMaker;
import code.resource.SpriteLoader;
import code.util.BufferUtils;
import code.util.ShaderUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;


public class Game implements Runnable {

	public final static int GAME_HEIGHT = 720;
	public final static int GAME_WIDTH = 1280;
	public static Game game;
	public boolean running = false;
	private String gameName = "Spaxel - Devbuild 0.3.2_exp";

	private Thread thread;
	private long window;
	//private BufferedImage image = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
	//private BufferedImage textBuffer = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
	//public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	//private int[] textReset = ((DataBufferInt) textBuffer.getRaster().getDataBuffer()).getData();

	public LoadingScreen loadingScreen;
	public SystemUpdater updater;
	public GLCapabilities capabilities;

	public static void main(String[] args) {
		game = new Game();

		/*
		*/
		game.start();
	}

	public Game() {
		loadingScreen = new LoadingScreen();
		updater = new SystemUpdater();
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		initialize();
		thread.start();
		Engine.getEngine().initialize();

	}

	public synchronized void stop() {
		running = false;
		try {
			glfwFreeCallbacks(window);
			glfwDestroyWindow(window);
		} finally {
			glfwTerminate();
			glfwSetErrorCallback(null).free();
		}

	}

	public void initialize(){
		GLFWErrorCallback.createPrint(System.err).set();

		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		window = glfwCreateWindow(GAME_WIDTH, GAME_HEIGHT, gameName, NULL, NULL);
		if (window == NULL) {
			System.err.println("Could not create GLFW window!");
			return;
		}

		//convert input
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in our rendering loop
		});

		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		glfwSetWindowPos(
				window,
				(vidmode.width() - GAME_WIDTH) / 2,
				(vidmode.height() - GAME_HEIGHT) / 2
		);


		glfwMakeContextCurrent(window);
		glfwShowWindow(window);

		GL.createCapabilities();
		capabilities = GL.getCapabilities();

		// Set the clear color
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		//glEnable(GL_DEPTH_TEST);
		glActiveTexture(GL_TEXTURE1);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		System.out.println("OpenGL: " + glGetString(GL_VERSION));
	}

	public void run() {
		GL.setCapabilities(Game.game.capabilities);

		int fps = 0;
		long accTime = 0;
		while (running) {
			if (glfwWindowShouldClose(window)){
				stop();
			}
			long start = System.nanoTime();
			if (!Engine.getEngine().isLoading()) {
				updater.generalUpdate();
				updater.renderUpdate();
			}
			if (Engine.getEngine().isLoading()) {
				renderLoading();
			} else {
				render();
			}
			fps++;
			long deltatime = System.nanoTime() - start;
			accTime+= deltatime;
			if ( accTime >= 20000000*50){
				glfwSetWindowTitle(window, gameName + " @ " + fps + " fps");
				fps = 0;
				accTime = 0;
			}
			Engine.getEngine().setUpdateTime((float)deltatime/ 20000000);
		}
	}

	private float i = 0;

	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

		updater.render();

		glfwSwapBuffers(window);

		glfwPollEvents();

		//TODO text rendering
		/*
		clearText();
		BufferStrategy bs = getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		Graphics g2 = textBuffer.getGraphics();
		updater.render(g2);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(textBuffer, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();*/
	}

	public void renderLoading(){
		//TODO loadingscreen
		/*
		clearText();
		BufferStrategy bs = getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		Graphics g2 = textBuffer.getGraphics();
		loadingScreen.render(g2);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(textBuffer, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
		*/
	}

	/*
	public void clearText(){
		for (int i = 0; i < GAME_WIDTH*GAME_HEIGHT; i++){
			textReset[i] = 0;
		}
	}*/

}
