package gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.GameButton;
import utils.LoadSave;
import utils.Constants.PanelConstants;
import utils.Constants.UI;
import utils.Constants.UI.ButtonsConstants;

public class Menu extends State implements StateMethods{
	
	private GameButton[] menuButtons;
	private BufferedImage splashLogo;

	public Menu(Game game) {
		super(game);
		this.loadButtons();
		this.getSplashLogo();
	}

	private void getSplashLogo() {
		this.splashLogo = LoadSave.getImage("/overlays/splash_logo.png");
	}

	private void loadButtons() {
		this.menuButtons = new GameButton[2];
		
		menuButtons[0] = new GameButton((int)PanelConstants.SCREEN_WIDTH/2 + ButtonsConstants.PLAY_OFFSET, 
										(int)PanelConstants.SCREEN_HEIGHT/2,
										ButtonsConstants.MENU_WIDTH,
										ButtonsConstants.MENU_HEIGHT,
										new String[]{"/buttons/play1.png", "/buttons/play2.png"}, 
										Gamestate.PLAYING);
		
		menuButtons[1] = new GameButton((int)PanelConstants.SCREEN_WIDTH/2, 
										(int)PanelConstants.SCREEN_HEIGHT/2 + ButtonsConstants.QUIT_OFFSET,
										ButtonsConstants.MENU_WIDTH,
										ButtonsConstants.MENU_HEIGHT,
										new String[]{"/buttons/quit2.png", "/buttons/quit1.png"}, 
										Gamestate.QUIT);
	}
	
	private void resetButtons() {
		for(GameButton button: menuButtons) {
			button.reset();
		}
	}

	@Override  
	public void update() {
		for(GameButton button: menuButtons) {
			button.update();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(this.splashLogo, PanelConstants.SCREEN_WIDTH/2 - UI.SPLASH_WIDTH, PanelConstants.TILE_SIZE, ButtonsConstants.MENU_WIDTH, ButtonsConstants.MENU_HEIGHT, null);
		for(GameButton button: menuButtons) {
			button.draw(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_ENTER:
				Gamestate.gameState = Gamestate.PLAYING;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_ENTER:
				break;
		}		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(GameButton button: menuButtons) {
			if(this.isOver(e, button)) {
				button.setMouseOver(true);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for(GameButton button: menuButtons) {
			if(this.isOver(e, button)) {
				if(button.isMouseOver()) {
					button.setGamestate();
				}
				break;
			}
		}
		this.resetButtons();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
