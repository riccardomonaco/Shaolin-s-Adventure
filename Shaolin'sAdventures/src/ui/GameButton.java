package ui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utils.Constants.ButtonsConstants;
import utils.LoadSave;

/**
 * This represents a generic game button 
 * 
 */
public class GameButton {

	private int xPosition;
	private int yPosition;
	private int width;
	private int height;
	private int imgIndex;
	
	private Rectangle hitBox;
	
	private boolean mouseOver;

	private Gamestate gameState;
	private BufferedImage[] buttonImgs;
	
	/**
	 * Builds a new game button setting
	 * his layout and positioning 
	 * 
	 * @param xPosition
	 * 		button x position
	 * @param yPosition
	 * 		button y position
	 * @param width
	 * 		button width
	 * @param height
	 * 		button height
	 * @param imgPath
	 * 		button image path
	 * @param gameState
	 * 		button associated gamestate
	 */
	public GameButton(int xPosition, int yPosition, int width, int height, String[] imgPath, Gamestate gameState) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.width = width;
		this.height = height;
		this.gameState = gameState;
		this.loadImages(imgPath);
		this.initHitBox();
	}
	
	/**
	 * Loads the button images
	 * 
	 * @param path
	 * 		button images files path
	 */
	private void loadImages(String[] path) {
		this.buttonImgs = new BufferedImage[2];
		this.buttonImgs = LoadSave.getButtonsImages(path);
	}
	
	/**
	 * Initializes the button hitbox
	 */
	private void initHitBox() {
		this.hitBox = new Rectangle(this.xPosition, this.yPosition, this.width, this.height);
	}
	
	/**
	 * Returns the button hitbox
	 * 
	 * @return hitbox
	 */
	public Rectangle returnHitBox() {
		return this.hitBox;
	}
	
	/**
	 * Returns if the mouse is pressing over the button
	 * 
	 * @return boolean mouseOver
	 */
	public boolean isMouseOver() {
		return mouseOver;
	}
	
	/**
	 * Sets the boolean mouse over value
	 * 
	 * @param mouseOver
	 */
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}
	
	/**
	 * Draws the button
	 * 
	 * @param g
	 * 		java drawing object
	 */
	public void draw(Graphics2D g) {
		g.drawImage(buttonImgs[imgIndex], this.xPosition, this.yPosition, this.width, this.height, null);
	}
	
	/**
	 * Updates the button image based on
	 * if it is being clicked
	 */
	public void update() {
		this.imgIndex = 0;
		if(mouseOver) this.imgIndex = 1; 
	}
	
	/**
	 * Set the main game state as the one associated
	 * at the button when pressed
	 */
	public void setGamestate() {
		Gamestate.gameState = this.gameState;
	}
	
	/**
	 * Resets the button state
	 */
	public void reset() {
		this.mouseOver = false;
	}
}
