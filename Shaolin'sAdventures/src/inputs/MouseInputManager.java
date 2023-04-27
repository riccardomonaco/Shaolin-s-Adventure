package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gamestates.Gamestate;
import main.GamePanel;

/**
 * This represent an implementation of MouseListener
 * 
 * This manages the keyboard inputs
 *
 */
public class MouseInputManager implements MouseListener {

	private GamePanel gamePanel;
	
	/**
	 * Builds a MouseInputManager
	 * 
	 * @param game panel containing the game object
	 */
	public MouseInputManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		switch(Gamestate.gameState) {
		case MENU:
			this.gamePanel.getGame().getMenu().mousePressed(e);
			break;
		case PLAYING:
			if(this.gamePanel.getGame().getPlaying().isDead) this.gamePanel.getGame().getPlaying().getGameOverOverlay().mousePressed(e);
			else if(this.gamePanel.getGame().getPlaying().isPaused) this.gamePanel.getGame().getPlaying().getPauseOverlay().mousePressed(e);
			else this.gamePanel.getGame().getPlaying().mousePressed(e);
		default:
			break;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		switch(Gamestate.gameState) {
		case MENU:
			this.gamePanel.getGame().getMenu().mouseReleased(e);
			break;
		case PLAYING:
			if(this.gamePanel.getGame().getPlaying().isDead) this.gamePanel.getGame().getPlaying().getGameOverOverlay().mouseReleased(e);
			else if(this.gamePanel.getGame().getPlaying().isPaused) this.gamePanel.getGame().getPlaying().getPauseOverlay().mouseReleased(e);
			else this.gamePanel.getGame().getPlaying().mouseReleased(e);
			break;
		default:
			break;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}
}
