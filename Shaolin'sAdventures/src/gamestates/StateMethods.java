package gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateMethods {
	
	public void update();
	public void draw(Graphics2D g);
	
	public void keyPressed(KeyEvent e);
	public void keyReleased(KeyEvent e);
	public void mousePressed(MouseEvent e);	
	public void mouseReleased(MouseEvent e);
	public void mouseClicked(MouseEvent e);
}
