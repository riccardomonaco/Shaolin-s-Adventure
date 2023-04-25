package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gamestates.Gamestate;
import main.GamePanel;

public class MouseInputManager implements MouseListener {

	private GamePanel gamePanel;
	
	public MouseInputManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

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

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
