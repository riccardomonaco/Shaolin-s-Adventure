package gamestates;

import java.awt.event.MouseEvent;

import main.Game;
import ui.GameButton;

public class State {
	
	protected Game game;
	
	public State(Game game) {
		
		this.game = game;
	}
	
	public boolean isOver(MouseEvent e, GameButton button) {
		return button.returnHitBox().contains(e.getX(), e.getY());
	}
	
	public Game getGame() {
		
		return this.game;
	}
}
