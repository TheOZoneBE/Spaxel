package code.input;

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard {
	private KeyState upState;
	private KeyState downState;
	private KeyState rightState;
	private KeyState leftState;
	private KeyState escState;
	private KeyState iState;
	private KeyState lState;
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

	/**
	 * @return the upState
	 */
	public KeyState getUpState() {
		return upState;
	}

	/**
	 * @param upState the upState to set
	 */
	public void setUpState(KeyState upState) {
		this.upState = upState;
	}

	/**
	 * @return the downState
	 */
	public KeyState getDownState() {
		return downState;
	}

	/**
	 * @param downState the downState to set
	 */
	public void setDownState(KeyState downState) {
		this.downState = downState;
	}

	/**
	 * @return the rightState
	 */
	public KeyState getRightState() {
		return rightState;
	}

	/**
	 * @param rightState the rightState to set
	 */
	public void setRightState(KeyState rightState) {
		this.rightState = rightState;
	}

	/**
	 * @return the leftState
	 */
	public KeyState getLeftState() {
		return leftState;
	}

	/**
	 * @param leftState the leftState to set
	 */
	public void setLeftState(KeyState leftState) {
		this.leftState = leftState;
	}

	/**
	 * @return the escState
	 */
	public KeyState getEscState() {
		return escState;
	}

	/**
	 * @param escState the escState to set
	 */
	public void setEscState(KeyState escState) {
		this.escState = escState;
	}

	/**
	 * @return the iState
	 */
	public KeyState getiState() {
		return iState;
	}

	/**
	 * @param iState the iState to set
	 */
	public void setiState(KeyState iState) {
		this.iState = iState;
	}

	/**
	 * @return the lState
	 */
	public KeyState getlState() {
		return lState;
	}

	/**
	 * @param lState the lState to set
	 */
	public void setlState(KeyState lState) {
		this.lState = lState;
	}
}