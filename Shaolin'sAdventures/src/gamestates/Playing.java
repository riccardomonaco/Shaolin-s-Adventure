package gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.loading.PrivateClassLoader;

import audio.AudioPlayer;
import entities.Entity;
import entities.EntityManager;
import entities.Gangster;
import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.GameOverOverlay;
import ui.PauseOverlay;

import static utils.HelpMethods.*;
import static utils.Constants.PanelConstants.*;

/**
 * This represent an extension of {@link gamestates.State}
 * 
 * and an implementation of {@link gamestates.StateMethods}
 * 
 * This represent the playing state
 *
 */
public class Playing extends State implements StateMethods {
		
	private Player player;
	private LevelManager levelManager;
	private EntityManager entityManager;
	private PauseOverlay pauseOverlay;
	private GameOverOverlay gameOverOverlay;

	public boolean isPaused;
	public boolean isDead;
	
	private int currentOffset = 0;
	private int maxOffset = SCREEN_WIDTH * 2;
	private int leftBorder = (int)(0.2 * SCREEN_WIDTH);
	private int rightBorder = (int)(0.8 * SCREEN_WIDTH);

	/**
	 * Builds the playing
	 * 
	 * @param game
	 */
	public Playing(Game game) {
		super(game);
		initClasses();
	}
	
	/**
	 * Initializes the necessary classes
	 */
	private void initClasses() {
		this.levelManager = new LevelManager(this.game);
		this.entityManager = new EntityManager(game, this.levelManager);
		this.player = new Player();
		this.player.setCurrentLevel(levelManager.getCurrentLevel());
		this.pauseOverlay = new PauseOverlay(this.game);
		this.gameOverOverlay = new GameOverOverlay(this.game);
		this.isPaused = false;
		this.isDead = false;
	}
	
	/**
	 * Returns the pause overlay
	 * 
	 * @return pause overlay
	 */
	public PauseOverlay getPauseOverlay() {
		return pauseOverlay;
	}
	
	/**
	 * Returns the gameover overlay
	 * 
	 * @return gameover overlay
	 */
	public GameOverOverlay getGameOverOverlay() {
		return this.gameOverOverlay;
	}
	
	/**
	 * Returns the actual player
	 * 
	 * @return player
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * Returns the level manager
	 * 
	 * @return level manager
	 */
	public LevelManager getLevelManager() {
		return this.levelManager;
	}
	
	/**
	 * Checks if the next player move will collide
	 * into the screen border
	 */
	private void checkCloseBorder() {
		int difference = ((int) player.getHitBox().x) - currentOffset;
		if(difference > rightBorder) currentOffset += difference - rightBorder;
		else if(difference < leftBorder) currentOffset += difference - leftBorder;
		if(currentOffset > maxOffset) currentOffset = maxOffset;
		else if(currentOffset < 0) currentOffset = 0;
	}
	
	/**
	 * Checks if a player is hitting
	 * an enemy
	 */
	private void checkPlayerHit() {
		for(Entity e: this.entityManager.currentEntities) {
			if(this.player.getHitBox().intersects(e.getHitBox())) {
				this.isDead = true;
				this.game.getAudioPlayer().stopSoundTrack(AudioPlayer.LEVEL);
				this.game.getAudioPlayer().playSoundEffects(AudioPlayer.GAMEOVER);
			}
		}
	}
	
	/**
	 * Checks if the player is fell off
	 * the buildings of the map
	 */
	private void checkPlayerFell() {
		if(isFellOff(this.player.getHitBox())) {
			this.isDead = true;
			this.game.getAudioPlayer().stopSoundTrack(AudioPlayer.LEVEL);
			this.game.getAudioPlayer().playSoundEffects(AudioPlayer.GAMEOVER);
		}
	}
	
	/**
	 * Checks if the player has been hit by a bullet
	 * 
	 */
	private void checkPlayerShot() {
		List<Gangster> gangsters = this.entityManager.currentEntities.stream()
																	 .filter(p -> p instanceof Gangster)
																	 .map(Gangster.class::cast)
																	 .collect(Collectors.toList());
		for(Gangster g: gangsters) {
			if(this.player.getHitBox().intersects(g.getBullet().getHitBox())) {
				this.isDead = true;
				this.game.getAudioPlayer().stopSoundTrack(AudioPlayer.LEVEL);
				this.game.getAudioPlayer().playSoundEffects(AudioPlayer.GAMEOVER);
			}
		}
	}
	
	/**
	 * resets the playing state
	 */
	public void resetPlaying() {
		this.isPaused = false;
		this.initClasses();
	}
	
	/**
	 * Returns current map offset
	 * 
	 * @return current x offset
	 */
	public int getCurrentOffset() {
		return this.currentOffset;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
		if(!isDead) {
			if(!isPaused) {
				this.checkPlayerShot();
				this.checkPlayerFell();
				this.checkPlayerHit();
				this.checkCloseBorder();
				this.levelManager.update();
				this.entityManager.update();
				this.player.update(this.currentOffset);
			}else {
				this.pauseOverlay.update();
			}
		}else {
			this.gameOverOverlay.update();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(Graphics2D g) {
		
		this.levelManager.draw(g, currentOffset);
		this.entityManager.draw(g, currentOffset);
		this.player.draw(g, currentOffset);
		if(isDead) {
			this.gameOverOverlay.draw(g);
		}else {
			if(isPaused) {
				this.pauseOverlay.draw(g);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mousePressed(MouseEvent e) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}
}

