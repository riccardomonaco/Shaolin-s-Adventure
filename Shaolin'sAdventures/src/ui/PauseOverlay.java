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

public class PauseOverlay extends State implements StateMethods{

	private BufferedImage pauseMenu;
	private GameButton[] pauseButtons;
	
	private int xPosition;
	private int yPosition;
	private int width;
	private int height;
	
	public PauseOverlay(Game game) {
		super(game);
		loadOverlay();
		loadButtons();
	}

	private void loadOverlay() {
		this.pauseMenu = LoadSave.getImage("/overlays/pause_menu_bg_title.png");
		this.width = TILE_SIZE * 6;
		this.height = TILE_SIZE * 6;
		this.xPosition = SCREEN_WIDTH/2 - TILE_SIZE*3;
		this.yPosition = SCREEN_HEIGHT/2 - TILE_SIZE*4;
		
	}
	
	private void loadButtons() {
		this.pauseButtons = new GameButton[3];
		
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
	}
	
	public void update() {
		for(GameButton b: pauseButtons) {
			b.update();
		}
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(new Color(128, 128, 128, 200));
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g.drawImage(pauseMenu, xPosition, yPosition, width, height, null);
		for(GameButton b: pauseButtons) {
			b.draw(g);
		}
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
		for(GameButton b: pauseButtons) {
			if(this.isOver(e, b)) {
				b.setMouseOver(true);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for(GameButton b: pauseButtons) {
			if(this.isOver(e, b)) {
				if(b.isMouseOver()) {
					b.setGamestate();
					if(b.equals(pauseButtons[1])) {
						this.game.getPlaying().resetPlaying();
					}
				}
			}
			b.reset();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
