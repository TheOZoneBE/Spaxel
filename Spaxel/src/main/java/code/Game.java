package code;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.*;

import code.engine.Engine;
import code.engine.LoadingScreen;
import code.engine.SystemUpdater;
import code.input.MouseWrapper;
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
	private MouseWrapper mouseWrapper;

	public LoadingScreen loadingScreen;
	public SystemUpdater updater;
	public GLCapabilities capabilities;

	public static void main(String[] args) {
		game = new Game();

		game.start();
	}

	public Game() {
		loadingScreen = new LoadingScreen();
		updater = new SystemUpdater();
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");

		thread.start();


	}

	public synchronized void stop() {
		running = false;
		updater.shutdown();
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

		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
		window = glfwCreateWindow(GAME_WIDTH, GAME_HEIGHT, gameName, NULL, NULL);
		if (window == NULL) {
			System.err.println("Could not create GLFW window!");
			return;
		}

		//convert input
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_X && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in our rendering loop
		});

		mouseWrapper = new MouseWrapper(window);


		glfwSetCursorPosCallback(window, mouseWrapper);

		Engine.getEngine().setMouseWrapper(mouseWrapper);
		Engine.getEngine().setWindow(window);

		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		glfwSetWindowPos(
				window,
				(vidmode.width() - GAME_WIDTH) / 2,
				(vidmode.height() - GAME_HEIGHT) / 2
		);



		glfwMakeContextCurrent(window);
		glfwShowWindow(window);



		// Set the clear color


		//glEnable(GL_DEPTH_TEST);



		GL.createCapabilities();
		capabilities = GL.getCapabilities();

		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glActiveTexture(GL_TEXTURE1);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		System.out.println("OpenGL: " + glGetString(GL_VERSION));
	}

	public void run() {
		initialize();

		Engine.getEngine().initialize();

		Thread load = new Thread(() -> Engine.getEngine().startLoading());
		load.start();

		Engine.getEngine().startLoading();

		int ups = 0;
		int fps = 0;
		long accTime = 0;
		long start = 0;
		long deltatime = 0;
		while (running) {
			if (glfwWindowShouldClose(window)){
				stop();
			}
			start = System.nanoTime();
			if (!Engine.getEngine().isLoading()) {
				if (accTime >= 20000000){
					accTime -= 20000000;
					updater.generalUpdate();
					ups++;
				}
			}
			if (Engine.getEngine().isLoading()) {
				renderLoading();
			} else {
				render();
			}
			fps++;
			deltatime = System.nanoTime() - start;
			accTime+= deltatime;
			if (ups == 50){
				glfwSetWindowTitle(window, gameName + " @ " + fps + " fps");
				fps = 0;
				accTime = 0;
				ups = 0;
			}
			Engine.getEngine().setUpdateTime((float)deltatime/ 20000000);
		}
	}

	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

		updater.render();

		glfwSwapBuffers(window);

		glfwPollEvents();
	}

	public void renderLoading(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

		updater.renderloading(loadingScreen);

		glfwSwapBuffers(window);

		glfwPollEvents();
	}

}
