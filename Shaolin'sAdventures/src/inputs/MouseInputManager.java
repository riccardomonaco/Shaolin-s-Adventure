package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gamestates.Gamestate;
import main.GamePanel;

public class MouseInputManager implements MouseListener{

	private GamePanel gamePanel;
	
	public MouseInputManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		switch(Gamestate.gameState) {
			case MENU:
				this.gamePanel.getGame().getMenu().mouseClicked(e);
				break;
			case PLAYING:
				this.gamePanel.getGame().getMenu().mouseClicked(e);
			default:
				break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch(Gamestate.gameState) {
		case MENU:
			this.gamePanel.getGame().getMenu().mousePressed(e);
			break;
		case PLAYING:
			this.gamePanel.getGame().getMenu().mousePressed(e);
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
			this.gamePanel.getGame().getMenu().mouseReleased(e);
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

}
