package code.util;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import code.Constants;

public final class GLUtil {
    private static final int TWO = 2;

    private GLUtil() {

    }

    public static class WindowCreateException extends Exception {
        private static final long serialVersionUID = 1;

        public WindowCreateException(String message) {
            super(message);
        }

    }

    public static long initGLWindow() throws WindowCreateException {
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, Constants.GL_MAJOR_V);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, Constants.GL_MINOR_V);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);

        long window = glfwCreateWindow(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, Constants.DISPLAY_NAME, NULL, NULL);
        if (window == NULL) {
            throw new WindowCreateException("Could not create window");
        }

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        glfwSetWindowPos(window, (vidmode.width() - Constants.GAME_WIDTH) / TWO,
                (vidmode.height() - Constants.GAME_HEIGHT) / TWO);

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
        return window;
    }

    public static void initGLContext() {
        GL.createCapabilities();

        glClearColor(0.0F, 0.0F, 0.0F, 1.0F);

        glActiveTexture(GL_TEXTURE1);

        glEnable(GL_BLEND);
        glEnable(GL_TEXTURE_2D);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }
}