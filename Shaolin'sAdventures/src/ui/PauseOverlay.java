package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage; 

import static utils.Constants.PanelConstants.*;

import utils.LoadSave;

public class PauseOverlay {

	private BufferedImage pauseMenu;
	private int xPosition;
	private int yPosition;
	private int width;
	private int height;
	
	public PauseOverlay() {
		
		loadOverlay();
	}

	private void loadOverlay() {
		this.pauseMenu = LoadSave.getImage("/overlays/pause_menu.png");
		this.width = TILE_SIZE * 4;
		this.height = TILE_SIZE * 4;
		this.xPosition = SCREEN_WIDTH/2 - TILE_SIZE*2;
		this.yPosition = SCREEN_HEIGHT/2 - TILE_SIZE*2;
		
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(new Color(128, 128, 128, 200));
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g.drawImage(pauseMenu, xPosition, yPosition, width, height, null);
	}
}
