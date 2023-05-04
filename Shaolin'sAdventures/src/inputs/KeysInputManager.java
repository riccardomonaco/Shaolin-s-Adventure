package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;
import main.GamePanel;

/**
 * This represent an implementation of KeyListener
 * 
 * This manages the keyboard inputs
 *
 */
public class KeysInputManager implements KeyListener{
	
	private GamePanel gamePanel;
	
	/**
	 * Builds a KeysInputManager
	 * 
	 * @param game panel containing the game object
	 */
	public KeysInputManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A:
			switch(Gamestate.gameState) {
			case MENU:
				this.gamePanel.getGame().getMenu().keyPressed(e);
				break;
			case PLAYING:
				this.gamePanel.getGame().getPlaying().keyPressed(e);
				break;
			default:
				break;
			}
			break;
			
		case KeyEvent.VK_D:
			switch(Gamestate.gameState) {
			case MENU:
				this.gamePanel.getGame().getMenu().keyPressed(e);
				break;
			case PLAYING:
				this.gamePanel.getGame().getPlaying().keyPressed(e);
				break;
			default:
				break;
			}
			break;
			
		case KeyEvent.VK_SPACE:
			switch(Gamestate.gameState) {
			case MENU:
				this.gamePanel.getGame().getMenu().keyPressed(e);
			case PLAYING:
				this.gamePanel.getGame().getPlaying().keyPressed(e);
				break;
			default:
				break;
			}
			break;
		
		case KeyEvent.VK_ENTER:
			switch(Gamestate.gameState) {
			case MENU:
				this.gamePanel.getGame().getMenu().keyPressed(e);
			case PLAYING:
				this.gamePanel.getGame().getPlaying().keyPressed(e);
				break;
			default:
				break;
			}
			break;
			
		case KeyEvent.VK_ESCAPE:
			switch (Gamestate.gameState) {
			case MENU:
				break;
			case PLAYING:
				this.gamePanel.getGame().getPlaying().keyPressed(e);
			default:
				break;	
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A:
			switch(Gamestate.gameState) {
			case MENU:
				this.gamePanel.getGame().getMenu().keyReleased(e);
				break;
			case PLAYING:
				this.gamePanel.getGame().getPlaying().keyReleased(e);
				break;
			default:
				break;
			}
			break;
			
		case KeyEvent.VK_D:
			switch(Gamestate.gameState) {
			case MENU:
				this.gamePanel.getGame().getMenu().keyReleased(e);
				break;
			case PLAYING:
				this.gamePanel.getGame().getPlaying().keyReleased(e);
				break;
			default:
				break;
			}
			break;
			
		case KeyEvent.VK_SPACE:
			switch(Gamestate.gameState) {
			case MENU:
				this.gamePanel.getGame().getMenu().keyReleased(e);
				break;
			case PLAYING:
				this.gamePanel.getGame().getPlaying().keyReleased(e);
				break;
			default:
				break;
			}
			break;
			
		case KeyEvent.VK_ENTER:
			switch(Gamestate.gameState) {
			case MENU:
				this.gamePanel.getGame().getMenu().keyReleased(e);
			case PLAYING:
				this.gamePanel.getGame().getPlaying().keyReleased(e);
				break;
			default:
				break;
			}
			break;
		}
	}
}

