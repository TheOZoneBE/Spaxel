package code.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import code.Constants;

import java.awt.event.MouseEvent;

import static org.lwjgl.glfw.GLFW.*;

public class MouseWrapper extends GLFWCursorPosCallback {

	private int x;
	private int y;
	public boolean mouse1;
	public boolean mouse2;
	public boolean mouse3;
	private long window;

	public MouseWrapper(long window) {
		this.window = window;
	}

	public void update() {
		mouse1 = glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_1) == GLFW_PRESS;
		mouse2 = glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_2) == GLFW_PRESS;
		mouse3 = glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_3) == GLFW_PRESS;
	}

	public void mousePressed(MouseEvent m) {
		if (MouseEvent.BUTTON1 == m.getButton()) {
			mouse1 = true;
		}
		if (MouseEvent.BUTTON2 == m.getButton()) {
			mouse2 = true;
		}
		if (MouseEvent.BUTTON3 == m.getButton()) {
			mouse3 = true;
		}
	}

	public void mouseReleased(MouseEvent m) {
		if (MouseEvent.BUTTON1 == m.getButton()) {
			mouse1 = false;
		}
		if (MouseEvent.BUTTON2 == m.getButton()) {
			mouse2 = false;
		}
		if (MouseEvent.BUTTON3 == m.getButton()) {
			mouse3 = false;
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
}
