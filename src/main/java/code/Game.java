package code;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.*;

import code.engine.Engine;
import code.input.MouseWrapper;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Game implements Runnable {
	public static final Logger LOGGER = Logger.getLogger(Game.class.getName());
	public static final double MOUSE_FOLLOW_CUTOFF = .1;

	private boolean running;
	private String gameName = "Spaxel - Devbuild 0.3.2_exp";

	private long window;

	private Game() {

	}

	public static void main(String[] args) {
		Game gameInstance = new Game();

		gameInstance.start();
	}

	public synchronized void start() {
		running = true;
		Thread thread = new Thread(this, "Display");

		thread.start();

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

	public void initialize() {
		GLFWErrorCallback.createPrint().set();

		if (!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}

		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 5);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
		window = glfwCreateWindow(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, gameName, NULL, NULL);
		if (window == NULL) {
			LOGGER.log(Level.SEVERE, "Could not create GLFW window!");
			return;
		}

		// convert input
		glfwSetKeyCallback(window, (long windowID, int key, int scancode, int action, int mods) -> {
			if (key == GLFW_KEY_X && action == GLFW_RELEASE) {
				// We will detect this in our rendering loop
				glfwSetWindowShouldClose(window, true);
			}
		});

		MouseWrapper mouseWrapper = new MouseWrapper(window);

		glfwSetCursorPosCallback(window, mouseWrapper);

		Engine.getEngine().setMouseWrapper(mouseWrapper);
		Engine.getEngine().setWindow(window);

		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		glfwSetWindowPos(window, (vidmode.width() - Constants.GAME_WIDTH) / 2,
				(vidmode.height() - Constants.GAME_HEIGHT) / 2);

		glfwMakeContextCurrent(window);
		glfwShowWindow(window);

		GL.createCapabilities();

		glClearColor(0.0F, 0.0F, 0.0F, 0.0F);

		glActiveTexture(GL_TEXTURE1);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		LOGGER.log(Level.INFO, "OpenGL: {0}", glGetString(GL_VERSION));
	}

	public void run() {
		initialize();

		Engine.getEngine().initialize();

		Thread load = new Thread(() -> Engine.getEngine().startLoading());
		load.start();

		int ups = 0;
		int fps = 0;
		long accTime = 0;
		long start;
		long deltatime;
		while (running) {
			if (glfwWindowShouldClose(window)) {
				stop();
			}
			start = System.nanoTime();
			if (!Engine.getEngine().isLoading() && accTime >= 20000000) {
				accTime -= 20000000;
				Engine.getEngine().getUpdater().generalUpdate();
				ups++;
			}
			if (Engine.getEngine().isLoading()) {
				renderLoading();

			} else {
				render();
			}
			fps++;
			deltatime = System.nanoTime() - start;
			accTime += deltatime;
			if (ups == 50) {
				glfwSetWindowTitle(window, gameName + " @ " + fps + " fps");
				fps = 0;
				ups = 0;
			}
			Engine.getEngine().setUpdateTime(deltatime / 20000000D);
		}
	}

	public void render() {
		// clear the framebuffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		Engine.getEngine().getUpdater().render();

		glfwSwapBuffers(window);

		glfwPollEvents();
	}

	public void renderLoading() {
		// clear the framebuffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		Engine.getEngine().getUpdater().renderloading(Engine.getEngine().getLoadingScreen());

		glfwSwapBuffers(window);

		glfwPollEvents();
	}

}
