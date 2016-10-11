package code.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard{
	//private boolean[] keys = new boolean[512];
	//public boolean[] previous = new boolean[512];
	public boolean up,down,left,right,esc;
	//public int upCode, downCode, leftCode, rightCode, escCode;
	private long window;



	public Keyboard(long window){
		this.window = window;
		/*
		upCode = KeyEvent.VK_UP;
		downCode = KeyEvent.VK_DOWN;
		leftCode = KeyEvent.VK_LEFT;
		rightCode = KeyEvent.VK_RIGHT;
		escCode = KeyEvent.VK_ESCAPE;*/
	}

	public void update(){
		/*
		up = keys[upCode];
		down = keys[downCode];
		left = keys[leftCode];
		right = keys[rightCode];
		esc = keys[escCode];*/
		up = glfwGetKey(window, GLFW_KEY_UP ) == GLFW_PRESS;
		down = glfwGetKey(window, GLFW_KEY_DOWN ) == GLFW_PRESS;
		left = glfwGetKey(window, GLFW_KEY_LEFT ) == GLFW_PRESS;
		right = glfwGetKey(window, GLFW_KEY_RIGHT ) == GLFW_PRESS;
		esc = glfwGetKey(window, GLFW_KEY_ESCAPE ) == GLFW_PRESS;
	}
	/*
	public void keyPressed(KeyEvent k) {
		previous[k.getKeyCode()] = keys[k.getKeyCode()];
		keys[k.getKeyCode()] = true;
	}

	
	public void keyReleased(KeyEvent k) {
		previous[k.getKeyCode()] = keys[k.getKeyCode()];
		keys[k.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {

	}*/

}
