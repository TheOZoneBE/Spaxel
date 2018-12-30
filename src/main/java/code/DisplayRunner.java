package code;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import code.engine.Engine;
import code.input.MouseWrapper;
import code.system.RenderSystem;
import org.lwjgl.glfw.GLFWErrorCallback;

import java.util.logging.Level;
import java.util.logging.Logger;
import code.util.GLUtil;

public class DisplayRunner implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(DisplayRunner.class.getName());

    private boolean running = true;

    private long window = NULL;
    private RenderSystem renderSystem;

    public DisplayRunner() {
        super();
    }

    public void initialize() {
        GLFWErrorCallback.createPrint().set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // init window
        try {
            window = GLUtil.initGLWindow();
        } catch (GLUtil.WindowCreateException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Game.exit();
        }

        // init gl context
        GLUtil.initGLContext();

        LOGGER.log(Level.INFO, "OpenGL: {0}", glGetString(GL_VERSION));

        // setup mouse
        MouseWrapper mouseWrapper = new MouseWrapper(window);
        glfwSetCursorPosCallback(window, mouseWrapper);

        Engine.getEngine().setMouseWrapper(mouseWrapper);
        Engine.getEngine().setWindow(window);
    }

    public void run() {
        initialize();

        Engine.getEngine().initialize();

        renderSystem = new RenderSystem();

        Thread load = new Thread(() -> Engine.getEngine().startLoading());
        load.start();

        long start;
        long deltatime;
        while (running) {
            if (glfwWindowShouldClose(window)) {
                Game.exit();
            }
            start = System.nanoTime();
            render();
            deltatime = System.nanoTime() - start;
            Engine.getEngine().setUpdateTime((double) deltatime / Constants.NS_PER_TICK);
        }
    }

    public void render() {
        // clear the framebuffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        if (Engine.getEngine().getGameState() == Engine.GameState.LOAD) {
            renderSystem.renderloading(Engine.getEngine().getLoadingScreen());
        } else {
            renderSystem.update();
        }

        glfwSwapBuffers(window);

        glfwPollEvents();
    }

    public void exit() {
        running = false;
        try {
            glfwFreeCallbacks(window);
            glfwDestroyWindow(window);
        } finally {
            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
    }
}