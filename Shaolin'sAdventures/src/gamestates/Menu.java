package gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import audio.AudioPlayer;
import main.Game;
import ui.GameButton;
import utils.LoadSave;
import utils.Constants.PanelConstants;
import utils.Constants.UIConstants;
import utils.Constants.ButtonsConstants;

/**
 * This represent an extension of {@link gamestates.State}
 * 
 * and an implementation of {@link gamestates.StateMethods}
 * 
 * This represent the menu of the game
 *
 */
public class Menu extends State implements StateMethods{
	
	private GameButton[] menuButtons;
	private BufferedImage menuBackground;
	private BufferedImage splashLogo;

	/**
	 * Builds a Menu loading the images and
	 * the buttons
	 * 
	 * @param game
	 */
	public Menu(Game game) {
		super(game);
		this.loadButtons();
		this.loadImages();
	}

	/**
	 * Retrieves the images for the ui
	 */
	private void loadImages() {
		this.splashLogo = LoadSave.getImage("/overlays/splash_logo.png");
		this.menuBackground = LoadSave.getImage("/overlays/menu_bg.png");
	}

	/**
	 * Initializes the menu buttons
	 */
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
	 
	/**
	 * Resets the state of the menu buttons
	 */
	private void resetButtons() {
		for(GameButton button: menuButtons) {
			button.reset();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override  
	public void update() {
		for(GameButton button: menuButtons) {
			button.update();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(this.menuBackground, 0, 0, PanelConstants.SCREEN_WIDTH, PanelConstants.SCREEN_HEIGHT, null);
		g.drawImage(this.splashLogo, PanelConstants.SCREEN_WIDTH/2 - UIConstants.SPLASH_WIDTH, PanelConstants.TILE_SIZE, ButtonsConstants.MENU_WIDTH, ButtonsConstants.MENU_HEIGHT, null);
		for(GameButton button: menuButtons) {
			button.draw(g);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_ENTER:
				Gamestate.gameState = Gamestate.PLAYING;
				break;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_ENTER:
				break;
		}		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		for(GameButton button: menuButtons) {
			if(this.isOver(e, button)) {
				button.setMouseOver(true);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		for(GameButton button: menuButtons) {
			if(this.isOver(e, button)) {
				if(button.isMouseOver()) {
					button.setGamestate();
					if(Gamestate.gameState == Gamestate.PLAYING) this.game.getAudioPlayer().playSoundTrack(AudioPlayer.LEVEL);
					else if(Gamestate.gameState == Gamestate.MENU) this.game.getAudioPlayer().playSoundTrack(AudioPlayer.MENU);
				}
				break;
			}
		}
		this.resetButtons();
	}
}
