package code.input;

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard {
	public KeyState upState, downState, rightState, leftState, escState, iState, lState;
	private long window;


	public Keyboard(long window) {
		this.window = window;
		upState = new KeyState();
		downState = new KeyState();
		rightState = new KeyState();
		leftState = new KeyState();
		escState = new KeyState();
		iState = new KeyState();
		lState = new KeyState();
	}

	public void update() {
		upState.setState(glfwGetKey(window, GLFW_KEY_UP) == GLFW_PRESS);
		downState.setState(glfwGetKey(window, GLFW_KEY_DOWN) == GLFW_PRESS);
		leftState.setState(glfwGetKey(window, GLFW_KEY_LEFT) == GLFW_PRESS);
		rightState.setState(glfwGetKey(window, GLFW_KEY_RIGHT) == GLFW_PRESS);
		escState.setState(glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS);
		iState.setState(glfwGetKey(window, GLFW_KEY_I) == GLFW_PRESS);
		lState.setState(glfwGetKey(window, GLFW_KEY_L) == GLFW_PRESS);
	}
}