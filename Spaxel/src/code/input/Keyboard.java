package code.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	private boolean[] keys = new boolean[512];
	public boolean up,down,left,right;

	public void update(){
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
	}
	
	public void keyPressed(KeyEvent k) {
		keys[k.getKeyCode()] = true;	
		
	}

	
	public void keyReleased(KeyEvent k) {
		keys[k.getKeyCode()] = false;
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
