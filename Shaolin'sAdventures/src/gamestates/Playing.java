package gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.management.loading.PrivateClassLoader;

import entities.Entity;
import entities.EntityManager;
import entities.Gangster;
import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;

import static utils.Constants.PanelConstants.*;

public class Playing extends State implements StateMethods{
		
	private Player player;
	private LevelManager levelManager;
	private EntityManager entityManager;
	private PauseOverlay pauseOverlay;
	
	private boolean isPaused;
	
	private int currentOffset = 0;
	private int maxOffset = SCREEN_WIDTH * 2;
	private int leftBorder = (int)(0.2 * SCREEN_WIDTH);
	private int rightBorder = (int)(0.8 * SCREEN_WIDTH);

	public Playing(Game game) {
		
		super(game);
		initClasses();
	}
	
	private void initClasses() {
		
		this.levelManager = new LevelManager(this.game);
		this.entityManager = new EntityManager(game, this.levelManager);
		this.player = new Player();
		this.player.setCurrentLevel(levelManager.getCurrentLevel());
		this.pauseOverlay = new PauseOverlay();
		this.isPaused = false;
	}
		

	public Player getPlayer() {
		
		return this.player;
	}
		
	public LevelManager getLevelManager() {
			
		return this.levelManager;
	}

	@Override
	public void update() {
		if(!isPaused) {
			checkCloseBorder();
			this.levelManager.update();
			this.entityManager.update();
			this.player.update();
		}
	}

	private void checkCloseBorder() {
		
		int difference = ((int) player.getHitBox().x) - currentOffset;
		
		if(difference > rightBorder) currentOffset += difference - rightBorder;
		else if(difference < leftBorder) currentOffset += difference - leftBorder;
		
		if(currentOffset > maxOffset) currentOffset = maxOffset;
		else if(currentOffset < 0) currentOffset = 0;
		
		this.setPlayerOffset();
		
	}
	
	public void setPlayerOffset() {
		
		this.player.setOffset(currentOffset);
	}

	@Override
	public void draw(Graphics2D g) {
		
		this.levelManager.draw(g, currentOffset);
		this.entityManager.draw(g, currentOffset);
		this.player.draw(g);
		if (isPaused) {
			this.pauseOverlay.draw(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A:
			this.player.setLeft(true);
			break;
		case KeyEvent.VK_D:
			this.player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			this.player.setJump(true);
			break;
		case KeyEvent.VK_ESCAPE:
			this.isPaused = !isPaused;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A:
			this.player.setLeft(false);
			break;
		case KeyEvent.VK_D:
			this.player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			this.player.setJump(false);
			break;
		}		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

