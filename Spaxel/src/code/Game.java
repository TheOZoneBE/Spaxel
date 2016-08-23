package code;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import code.engine.LoadingScreen;
import code.engine.SystemUpdater;
import code.graphics.Spritesheet;
import code.math.MatrixF;
import code.math.MatrixMaker;
import code.util.BufferUtils;
import code.util.ShaderUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;


public class Game implements Runnable {

	private static final long serialVersionUID = 1L;
	public final static int GAME_HEIGHT = 720;
	public final static int GAME_WIDTH = 1280;
	public static Game game;
	public boolean running = false;
	private String gameName = "Spaxel - Devbuild 0.3.1_exp";

	private Thread thread;
	private long window;
	private BufferedImage image = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage textBuffer = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
	public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] textReset = ((DataBufferInt) textBuffer.getRaster().getDataBuffer()).getData();

	public LoadingScreen loadingScreen;
	public SystemUpdater updater;

	public float[] testVertices = new float[]{
			-160,-160,0,
			-160,160,0,
			160,160,0,
			160,-160,0
	};

	byte[] indices = new byte[] {
			0, 1, 3,
			3, 1, 2
	};

	float[] tcs = new float[] {
			0, 0,
			0, 1,
			1, 1,
			1, 0
	};

	int shaderprogram;

	public static void main(String[] args) {
		game = new Game();

		/*
		*/
		game.start();
	}

	public Game() {
		/*
		loadingScreen = new LoadingScreen();
		updater = new SystemUpdater();*/
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
		//Engine.getEngine().initialize();
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

	private int vbo, vao,ibo, tbo;
	private Spritesheet test;

	public void intitialize(){
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

		// Set the clear color
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		glEnable(GL_DEPTH_TEST);
		glActiveTexture(GL_TEXTURE1);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		System.out.println("OpenGL: " + glGetString(GL_VERSION));

		shaderprogram = ShaderUtils.load("/shaders/2Dsprite.vert", "/shaders/2Dsprite.frag");

		MatrixF projection_matrix = MatrixMaker.orthographic(-GAME_WIDTH/2, GAME_WIDTH/2, -GAME_HEIGHT/2, GAME_HEIGHT/2, -1.0f, 1.0f);

		glUseProgram(shaderprogram);

		int uniform_pr = glGetUniformLocation(shaderprogram, "projection_matrix");
		int uniform_sa = glGetUniformLocation(shaderprogram, "tex_sampler");

		glUniformMatrix4fv(uniform_pr, false, projection_matrix.toFloatBuffer());
		glUniform1i(uniform_sa, 1);

		vao = glGenVertexArrays();
		glBindVertexArray(vao);

		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(testVertices), GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(0);

		tbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, tbo);
		glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(tcs), GL_STATIC_DRAW);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(1);

		ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createByteBuffer(indices), GL_STATIC_DRAW);

		test = new Spritesheet(32,32,"/spritesheets/ships.png");
		glBindTexture(GL_TEXTURE_2D, test.getId());
		/*
		Shader.loadAll();

		Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);
		Shader.BG.setUniformMat4f("pr_matrix", pr_matrix);
		Shader.BG.setUniform1i("tex", 1);

		Shader.BIRD.setUniformMat4f("pr_matrix", pr_matrix);
		Shader.BIRD.setUniform1i("tex", 1);

		Shader.PIPE.setUniformMat4f("pr_matrix", pr_matrix);
		Shader.PIPE.setUniform1i("tex", 1);*/
	}

	public void run() {
		intitialize();


		int ups = 0;
		int fps = 0;
		long accTime = 20000000;
		while (running) {
			if (glfwWindowShouldClose(window)){
				stop();
			}
			render();
			/*
			long start = System.nanoTime();
			if (accTime >= 20000000) {
				accTime -= 20000000;
				if (!Engine.getEngine().isLoading()) {
					updater.generalUpdate();
					updater.renderUpdate();
				}
				ups++;
			}
			updater.renderUpdate();
			if (Engine.getEngine().isLoading()) {
				renderLoading();
			} else {
				render();
			}
			fps++;

			if (ups == 50) {
				frame.setTitle(gameName + " @ " + fps + " fps");
				ups = 0;
				fps = 0;
			}
			long deltatime = System.nanoTime() - start;
			Engine.getEngine().setUpdateTime((float)deltatime/ 20000000);
			accTime += deltatime;*/
		}


	}

	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
		glBindTexture(GL_TEXTURE_2D, test.getId());
		glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_BYTE, 0);
		glfwSwapBuffers(window); // swap the color buffers

		// Poll for window events. The key callback above will only be
		// invoked during this call.
		glfwPollEvents();

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

	public void clearText(){
		for (int i = 0; i < GAME_WIDTH*GAME_HEIGHT; i++){
			textReset[i] = 0;
		}
	}

}
