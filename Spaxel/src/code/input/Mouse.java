package code.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

	private int x;
	private int y;
	public boolean mouse1, mouse2, mouse3;

	public void mousePressed(MouseEvent m) {
		if (MouseEvent.BUTTON1 == m.getButton())
			mouse1 = true;
		if (MouseEvent.BUTTON2 == m.getButton())
			mouse2 = true;
		if (MouseEvent.BUTTON3 == m.getButton())
			mouse3 = true;

	}

	public void mouseReleased(MouseEvent m) {
		if (MouseEvent.BUTTON1 == m.getButton())
			mouse1 = false;
		if (MouseEvent.BUTTON2 == m.getButton())
			mouse2 = false;
		if (MouseEvent.BUTTON3 == m.getButton())
			mouse3 = false;

	}

	public void mouseDragged(MouseEvent m) {
		x = m.getX();
		y = m.getY();

	}

	public void mouseMoved(MouseEvent m) {
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
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
