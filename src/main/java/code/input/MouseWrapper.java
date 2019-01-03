package code.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import code.Constants;
import code.math.VectorD;
import java.awt.event.MouseEvent;
import static org.lwjgl.glfw.GLFW.*;

public class MouseWrapper extends GLFWCursorPosCallback {

	private int x;
	private int y;
	private KeyState mouse1;
	private KeyState mouse2;
	private KeyState mouse3;
	private long window;

	public MouseWrapper(long window) {
		this.window = window;
		mouse1 = new KeyState();
		mouse2 = new KeyState();
		mouse3 = new KeyState();
	}

	public void update() {
		mouse1.setState(glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_1) == GLFW_PRESS);
		mouse2.setState(glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_2) == GLFW_PRESS);
		mouse3.setState(glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_3) == GLFW_PRESS);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public VectorD getPos() {
		return new VectorD(x, y);
	}

	@Override
	public void invoke(long l, double xPos, double yPos) {
		x = (int) xPos;
		y = Constants.GAME_HEIGHT - (int) yPos;
	}

	/**
	 * @return the mouse3
	 */
	public KeyState getMouse3() {
		return mouse3;
	}

	/**
	 * @return the mouse2
	 */
	public KeyState getMouse2() {
		return mouse2;
	}

	/**
	 * @return the mouse1
	 */
	public KeyState getMouse1() {
		return mouse1;
	}

}
