package code.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard {
	public KeyState upState, downState, rightState, leftState, escState;
	private long window;


	public Keyboard(long window) {
		this.window = window;
		upState = new KeyState();
		downState = new KeyState();
		rightState = new KeyState();
		leftState = new KeyState();
		escState = new KeyState();
	}

	public void update() {
		upState.setState(glfwGetKey(window, GLFW_KEY_UP) == GLFW_PRESS);
		downState.setState(glfwGetKey(window, GLFW_KEY_DOWN) == GLFW_PRESS);
		leftState.setState(glfwGetKey(window, GLFW_KEY_LEFT) == GLFW_PRESS);
		rightState.setState(glfwGetKey(window, GLFW_KEY_RIGHT) == GLFW_PRESS);
		escState.setState(glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS);
	}
}