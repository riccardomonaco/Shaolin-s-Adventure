package ui;

import static utils.Constants.PanelConstants.SCREEN_HEIGHT;
import static utils.Constants.PanelConstants.SCREEN_WIDTH;
import static utils.Constants.PanelConstants.TILE_SIZE;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import audio.AudioPlayer;
import gamestates.Gamestate;
import gamestates.State;
import gamestates.StateMethods;
import main.Game;
import utils.LoadSave;
import utils.Constants.PanelConstants;
import utils.Constants.ButtonsConstants;

/**
 * This represents the overlay displayed on game over
 *
 */
public class GameOverOverlay extends State implements StateMethods{
	
	private BufferedImage gameOver;
	private GameButton homeButton;
	
	private int xPosition;
	private int yPosition;
	private int width;
	private int height;

	/**
	 * Builds a new game over overlay
	 * 
	 * @param game
	 */
	public GameOverOverlay(Game game) {
		super(game);
		loadOverlay();
		loadButtons();
	}

	/**
	 * Loads the images and sets the layout values
	 */
	private void loadOverlay() {
		this.gameOver = LoadSave.getImage("/overlays/gameover.png");
		this.width = TILE_SIZE * 6;
		this.height = TILE_SIZE * 6;
		this.xPosition = SCREEN_WIDTH/2 - TILE_SIZE*3;
		this.yPosition = SCREEN_HEIGHT/2 - TILE_SIZE*4;
	}
	
	/**
	 * Initializes the button shown on the overlay
	 */
	private void loadButtons() {
		this.homeButton = new GameButton((int)PanelConstants.SCREEN_WIDTH/2 - 64, 
										 (int)PanelConstants.SCREEN_HEIGHT/2 + 128,
										 ButtonsConstants.PAUSE_WIDTH, 
										 ButtonsConstants.PAUSE_HEIGHT,
										 new String[]{"/buttons/home1.png", "/buttons/home2.png"}, 
										 Gamestate.MENU);
	}
	
	/**
	 * Updates the button of the overlay
	 */
	public void update() {
		homeButton.update();
	}
	
	/**
	 * Draws the overlay and the button
	 */
	public void draw(Graphics2D g) {
		g.setColor(new Color(128, 128, 128, 200));
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g.drawImage(gameOver, xPosition, yPosition, width, height, null);
		homeButton.draw(g);
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
		if(this.isOver(e, homeButton)) {
			homeButton.setMouseOver(true);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.isOver(e, homeButton)) {
			if(homeButton.isMouseOver()) {
				homeButton.setGamestate();
				this.game.getPlaying().restartPlaying();
				this.game.getAudioPlayer().playSoundTrack(AudioPlayer.MENU);
			}
		}
		homeButton.reset();
	}
}