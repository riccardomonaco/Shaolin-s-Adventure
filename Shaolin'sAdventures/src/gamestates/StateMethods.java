package gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Represents a the contract for a generic application of a game state.
 * {@link gamestates.Menu}
 * {@link gamestates.Playing}
 * 
 */
public interface StateMethods {
	
	/**
	 * Updates the state and his elements
	 */
	void update();
	
	/**
	 * Draws the state and his elements
	 * 
	 * @param g
	 * 		java drawing object
	 */
	void draw(Graphics2D g);
	
	/**
	 * Manages the behavior of the state when
	 * a key is pressed
	 * 
	 * @param e
	 * 		key info
	 */
	void keyPressed(KeyEvent e);
	
	/**
	 * Manages the behavior of the state when
	 * a key is released
	 * 
	 * @param e
	 * 		key info
	 */
	void keyReleased(KeyEvent e);
	
	/**
	 * Manages the behavior of the state when
	 * left mouse button gets pressed
	 * 
	 * @param e
	 * 		mouse info
	 */
	void mousePressed(MouseEvent e);
	
	/**
	 * Manages the behavior of the state when
	 * left mouse button gets released
	 * 
	 * @param e
	 * 		mouse info
	 */
	void mouseReleased(MouseEvent e);
}
