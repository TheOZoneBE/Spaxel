package code.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	private boolean[] keys = new boolean[512];
	public boolean[] previous = new boolean[512];
	public boolean up,down,left,right,esc;
	public int upCode, downCode, leftCode, rightCode, escCode;



	public Keyboard(){
		upCode = KeyEvent.VK_UP;
		downCode = KeyEvent.VK_DOWN;
		leftCode = KeyEvent.VK_LEFT;
		rightCode = KeyEvent.VK_RIGHT;
		escCode = KeyEvent.VK_ESCAPE;
	}

	public void update(){
		up = keys[upCode];
		down = keys[downCode];
		left = keys[leftCode];
		right = keys[rightCode];
		esc = keys[escCode];
	}
	
	public void keyPressed(KeyEvent k) {
		previous[k.getKeyCode()] = keys[k.getKeyCode()];
		keys[k.getKeyCode()] = true;
	}

	
	public void keyReleased(KeyEvent k) {
		previous[k.getKeyCode()] = keys[k.getKeyCode()];
		keys[k.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {

	}

}
