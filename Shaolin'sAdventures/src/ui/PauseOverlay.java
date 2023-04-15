package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.State;
import gamestates.StateMethods;
import main.Game;

import static utils.Constants.PanelConstants.*;

import utils.LoadSave;
import utils.Constants.PanelConstants;

public class PauseOverlay extends State implements StateMethods{

	private BufferedImage pauseMenu;
	
	private GameButton menuButton;
	
	private int xPosition;
	private int yPosition;
	private int width;
	private int height;
	
	public PauseOverlay(Game game) {
		super(game);
		loadOverlay();
		loadButton();
	}

	private void loadOverlay() {
		this.pauseMenu = LoadSave.getImage("/overlays/pause_menu.png");
		this.width = TILE_SIZE * 4;
		this.height = TILE_SIZE * 4;
		this.xPosition = SCREEN_WIDTH/2 - TILE_SIZE*2;
		this.yPosition = SCREEN_HEIGHT/2 - TILE_SIZE*2;
		
	}
	
	private void loadButton() {
		menuButton = new GameButton((int)PanelConstants.SCREEN_WIDTH/2 - 128, 
									(int)PanelConstants.SCREEN_HEIGHT/2 + 50, 
									new String[]{"/buttons/menu1.png", "/buttons/menu2.png"}, 
									Gamestate.MENU);
	}
	
	public void update() {
		this.menuButton.update();
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(new Color(128, 128, 128, 200));
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g.drawImage(pauseMenu, xPosition, yPosition, width, height, null);
		menuButton.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(this.isOver(e, this.menuButton)) {
			this.menuButton.setMouseOver(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.isOver(e, this.menuButton)) {
			if(this.menuButton.isMouseOver()) {
				this.menuButton.setGamestate();
			}
		}
		this.menuButton.reset();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
