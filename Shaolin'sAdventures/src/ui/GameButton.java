package ui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utils.Constants.UI.ButtonsCostants;
import utils.LoadSave;

public class GameButton {

	private int xPosition;
	private int yPosition;
	private int imgIndex;
	
	private Rectangle hitBox;
	
	private boolean mouseOver;

	private Gamestate gameState;
	private BufferedImage[] buttonImgs;
	
	public GameButton(int xPosition, int yPosition, String[] imgPath, Gamestate gameState) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.gameState = gameState;
		
		this.loadImages(imgPath);
		this.initHitBox();
	}
	
	private void loadImages(String[] path) {
		this.buttonImgs = new BufferedImage[2];
		this.buttonImgs = LoadSave.getButtonsImages(path);
	}
	
	private void initHitBox() {
		this.hitBox = new Rectangle(this.xPosition, this.yPosition + 50, ButtonsCostants.WIDTH, ButtonsCostants.HEIGHT - 125);
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
		g.drawImage(buttonImgs[imgIndex], this.xPosition, this.yPosition, ButtonsCostants.WIDTH, ButtonsCostants.HEIGHT, null);
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
