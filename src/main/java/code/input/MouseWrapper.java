package code.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import code.Constants;

import java.awt.event.MouseEvent;

import static org.lwjgl.glfw.GLFW.*;

public class MouseWrapper extends GLFWCursorPosCallback {

	private int x;
	private int y;
	private boolean mouse1;
	private boolean mouse2;
	private boolean mouse3;
	private long window;

	public MouseWrapper(long window) {
		this.window = window;
	}

	public void update() {
		setMouse1(glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_1) == GLFW_PRESS);
		setMouse2(glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_2) == GLFW_PRESS);
		setMouse3(glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_3) == GLFW_PRESS);
	}

	public void mousePressed(MouseEvent m) {
		if (MouseEvent.BUTTON1 == m.getButton()) {
			setMouse1(true);
		}
		if (MouseEvent.BUTTON2 == m.getButton()) {
			setMouse2(true);
		}
		if (MouseEvent.BUTTON3 == m.getButton()) {
			setMouse3(true);
		}
	}

	public void mouseReleased(MouseEvent m) {
		if (MouseEvent.BUTTON1 == m.getButton()) {
			setMouse1(false);
		}
		if (MouseEvent.BUTTON2 == m.getButton()) {
			setMouse2(false);
		}
		if (MouseEvent.BUTTON3 == m.getButton()) {
			setMouse3(false);
		}
	}

	public void mouseDragged(MouseEvent m) {
		mouseMove(m);
	}

	public void mouseMoved(MouseEvent m) {
		mouseMove(m);
	}

	public void mouseMove(MouseEvent m) {
		x = m.getX();
		y = m.getY();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void mouseClicked(MouseEvent arg0) {
		throw new UnsupportedOperationException();
	}

	public void mouseEntered(MouseEvent arg0) {
		throw new UnsupportedOperationException();
	}

	public void mouseExited(MouseEvent arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void invoke(long l, double xPos, double yPos) {
		x = (int) xPos;
		y = Constants.GAME_HEIGHT - (int) yPos;
	}

	/**
	 * @return the mouse3
	 */
	public boolean isMouse3() {
		return mouse3;
	}

	/**
	 * @param mouse3 the mouse3 to set
	 */
	public void setMouse3(boolean mouse3) {
		this.mouse3 = mouse3;
	}

	/**
	 * @return the mouse2
	 */
	public boolean isMouse2() {
		return mouse2;
	}

	/**
	 * @param mouse2 the mouse2 to set
	 */
	public void setMouse2(boolean mouse2) {
		this.mouse2 = mouse2;
	}

	/**
	 * @return the mouse1
	 */
	public boolean isMouse1() {
		return mouse1;
	}

	/**
	 * @param mouse1 the mouse1 to set
	 */
	public void setMouse1(boolean mouse1) {
		this.mouse1 = mouse1;
	}

}
