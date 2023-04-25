package ui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utils.Constants.UI.ButtonsConstants;
import utils.LoadSave;

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
	
	public GameButton(int xPosition, int yPosition, int width, int height, String[] imgPath, Gamestate gameState) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.width = width;
		this.height = height;
		this.gameState = gameState;
		
		this.loadImages(imgPath);
		this.initHitBox();
	}
	
	private void loadImages(String[] path) {
		this.buttonImgs = new BufferedImage[2];
		this.buttonImgs = LoadSave.getButtonsImages(path);
	}
	
	private void initHitBox() {
		this.hitBox = new Rectangle(this.xPosition, this.yPosition, this.width, this.height);
	}
	
	
	public Rectangle returnHitBox() {
		return this.hitBox;
	}
	
	public boolean isMouseOver() {
		return mouseOver;
	}
	
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(buttonImgs[imgIndex], this.xPosition, this.yPosition, this.width, this.height, null);
	}
	
	public void update() {
		this.imgIndex = 0;
		if(mouseOver) this.imgIndex = 1; 
	}
	
	public void setGamestate() {
		Gamestate.gameState = this.gameState;
	}
	
	public void reset() {
		this.mouseOver = false;
	}
}
