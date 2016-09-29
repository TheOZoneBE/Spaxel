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
	public GLCapabilities capabilities;

	public float[] testVertices = new float[]{
			-1,-1,0,
			-1,1,0,
			1,1,0,
			1,-1,0
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

	private int vbo, vao,ibo, tbo, textureID_Alpha, offset, sin_cos;
	private Spritesheet test;
	private int uniform_tr;
	private Map<String, SpriteData> sprites;

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

		shaderprogram = ShaderUtils.load("/shaders/2Dsprite.vert", "/shaders/2Dsprite.frag");

		MatrixF projection_matrix = MatrixMaker.orthographic(-GAME_WIDTH/2, GAME_WIDTH/2, -GAME_HEIGHT/2, GAME_HEIGHT/2, -1.0f, 1.0f);

		glUseProgram(shaderprogram);

		int uniform_pr = glGetUniformLocation(shaderprogram, "projection_matrix");
		int uniform_sa = glGetUniformLocation(shaderprogram, "tex_sampler");
		uniform_tr = glGetUniformLocation(shaderprogram, "transformation_matrix");

		MatrixF temp = MatrixMaker.getTransformationMatrix(150,150,50,.5f);

		glUniformMatrix4fv(uniform_pr, false, projection_matrix.toFloatBuffer());
		glUniform1i(uniform_sa, 1);
		glUniformMatrix3fv(uniform_tr, false, temp.toFloatBuffer());

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

		SpriteLoader loader = new SpriteLoader();
		sprites = loader.loadSprites("/resources/spritesheet.xml", "/resources/sprite.xml");
		test = loader.spritesheetMap.get("uisheet");
		//test = new Spritesheet(32,32,"/spritesheets/ships.png");
		glBindTexture(GL_TEXTURE_2D, test.getId());

		offset = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, offset);
		glEnableVertexAttribArray(2);
		glVertexAttribPointer(2,4,GL_FLOAT, false, 4*4,0);
		glVertexAttribDivisor(2,1);

		textureID_Alpha = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, textureID_Alpha);
		glEnableVertexAttribArray(3);
		glVertexAttribPointer(3,4,GL_FLOAT, false, 4*4,0);
		glVertexAttribDivisor(3,1);

		sin_cos = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, sin_cos);
		glEnableVertexAttribArray(4);
		glVertexAttribPointer(4,3,GL_FLOAT, false, 3*4,0);
		glVertexAttribDivisor(4,1);
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

		SpriteData classselect = sprites.get("button_hover");
		SpriteData classselect2 = sprites.get("class_select_click");

		glBindBuffer(GL_ARRAY_BUFFER, offset);
		glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(new float[]{50f,50f,64f, 16f, -50f, -50f, 24f, 24f}), GL_DYNAMIC_DRAW);

		glBindBuffer(GL_ARRAY_BUFFER, sin_cos);
		glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(new float[]{(float)Math.sin(i),(float) Math.cos(i), 1,(float) Math.sin(-i),(float) Math.cos(-i), 1}), GL_DYNAMIC_DRAW);



		glBindBuffer(GL_ARRAY_BUFFER, textureID_Alpha);
		glBufferData(GL_ARRAY_BUFFER, BufferUtils.combineFloatBuffers(classselect.getProperties(), classselect2.getProperties()), GL_DYNAMIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER,0);

		glBindTexture(GL_TEXTURE_2D, test.getId());
		glDrawElementsInstanced(GL_TRIANGLES, 6, GL_UNSIGNED_BYTE,0,2);

		glfwSwapBuffers(window); // swap the color buffers
		i+=.005;
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
