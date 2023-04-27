package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;

import gamestates.Gamestate;
import gamestates.State;
import gamestates.StateMethods;
import main.Game;

import static utils.Constants.PanelConstants.*;

import utils.LoadSave;
import utils.Constants.PanelConstants;
import utils.Constants.UI.ButtonsConstants;

/**
 * This represents the overlay displayed on pause screen
 *
 */
public class PauseOverlay extends State implements StateMethods{

	private BufferedImage pauseMenu;
	private GameButton[] pauseButtons;
	
	private int xPosition;
	private int yPosition;
	private int width;
	private int height;
	
	/**
	 * Builds a new pause overlay
	 * 
	 * @param game
	 */
	public PauseOverlay(Game game) {
		super(game);
		loadOverlay();
		loadButtons();
	}

	/**
	 * Loads the images and sets the layout values
	 */
	private void loadOverlay() {
		this.pauseMenu = LoadSave.getImage("/overlays/pause_menu_bg_alt.png");
		this.width = TILE_SIZE * 6;
		this.height = TILE_SIZE * 6;
		this.xPosition = SCREEN_WIDTH/2 - TILE_SIZE*3;
		this.yPosition = SCREEN_HEIGHT/2 - TILE_SIZE*4;
	}
	
	/**
	 * Initializes the buttons shown on the pause screen
	 */
	private void loadButtons() {
		this.pauseButtons = new GameButton[4];
		pauseButtons[0] = new GameButton(ButtonsConstants.HOME_BT_X, 
										 ButtonsConstants.HOME_BT_Y,
										 ButtonsConstants.PAUSE_WIDTH, 
										 ButtonsConstants.PAUSE_HEIGHT,
										 new String[]{"/buttons/home1.png", "/buttons/home2.png"}, 
										 Gamestate.MENU);
		pauseButtons[1] = new GameButton(ButtonsConstants.RESTART_BT_X, 
				 						 ButtonsConstants.RESTART_BT_Y,
				 						 ButtonsConstants.PAUSE_WIDTH, 
				 						 ButtonsConstants.PAUSE_HEIGHT,
										 new String[]{"/buttons/restart1.png", "/buttons/restart2.png"}, 
										 Gamestate.PLAYING);
		pauseButtons[2] = new GameButton(ButtonsConstants.VOLUME_BT_X, 
										 ButtonsConstants.VOLUME_BT_Y,
										 ButtonsConstants.PAUSE_WIDTH, 
										 ButtonsConstants.PAUSE_HEIGHT, 
										 new String[]{"/buttons/enable_volume1.png", "/buttons/enable_volume2.png"}, 
										 Gamestate.PLAYING);
		pauseButtons[3] = new GameButton(ButtonsConstants.VOLUME_BT_X, 
										 ButtonsConstants.VOLUME_BT_Y,
										 ButtonsConstants.PAUSE_WIDTH, 
										 ButtonsConstants.PAUSE_HEIGHT, 
										 new String[]{"/buttons/disable_volume1.png", "/buttons/disable_volume2.png"}, 
										 Gamestate.PLAYING);
	}
	
	/**
	 * Updates the buttons on the pause screen
	 */
	public void update() {
		for(GameButton b: pauseButtons) {
			b.update();
		}
	}
	
	/**
	 * Draws the pause screen and the buttons
	 */
	public void draw(Graphics2D g) {
		g.setColor(new Color(128, 128, 128, 200));
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g.drawImage(pauseMenu, xPosition, yPosition, width, height, null);
		for(GameButton b: pauseButtons) {
			b.draw(g);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyPressed(KeyEvent e) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyReleased(KeyEvent e) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		for(GameButton b: pauseButtons) {
			if(this.isOver(e, b)) {
				b.setMouseOver(true);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		for(GameButton b: pauseButtons) {
			if(this.isOver(e, b)) {
				if(b.isMouseOver()) {
					b.setGamestate();
					if(b.equals(pauseButtons[1])) {
						this.game.getPlaying().resetPlaying();
					}
					if(b.equals(pauseButtons[2])) {
						this.game.getAudioPlayer().setMute();
					}
				}
			}
			b.reset();
		}
	}
}
