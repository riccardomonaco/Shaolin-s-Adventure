package gamestates;

import java.awt.event.MouseEvent;

import audio.AudioPlayer;
import main.Game;
import ui.GameButton;

/**
 * Represents the generic state of the game
 *
 */
public class State {
	
	protected Game game;
	
	/**
	 * Builds a new state
	 * 
	 * @param game
	 */
	public State(Game game) {
		this.game = game;
	}
	
	/**
	 * Checks whether the mouse pointer is in a certain area
	 * respectively at the current state
	 * 
	 * @param e
	 * 		a mouse event
	 * @param button
	 * 		 button to check 
	 * @return boolean if it is over
	 */
	public boolean isOver(MouseEvent e, GameButton button) {
		return button.returnHitBox().contains(e.getX(), e.getY());
	}
	
	/**
	 * Returns the game
	 * @return game
	 */
	public Game getGame() {
		return this.game;
	}
}
