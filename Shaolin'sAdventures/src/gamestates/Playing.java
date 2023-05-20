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
import ui.GameCompletedOverlay;
import ui.GameOverOverlay;
import ui.PauseOverlay;

import static utils.HelpMethods.*;
import static utils.Constants.PanelConstants.*;

/**
 * This represents an extension of {@link gamestates.State}
 * 
 * and an implementation of {@link gamestates.StateMethods}
 * 
 * This represents the playing state
 *
 */
public class Playing extends State implements StateMethods {
		
	private Player player;
	private LevelManager levelManager;
	private EntityManager entityManager;
	private PauseOverlay pauseOverlay;
	private GameOverOverlay gameOverOverlay;
	private GameCompletedOverlay gameCompletedOverlay;

	public boolean isPaused;
	public boolean isDead;
	public boolean isCompleted;
	
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
		this.gameCompletedOverlay = new GameCompletedOverlay(this.game);
		this.isPaused = false;
		this.isDead = false;
		this.isCompleted = false;
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
	 * Returns the game completed overlay
	 * 
	 * @return game completed overlay
	 */
	public GameCompletedOverlay getGameCompletedOverlay() {
		return this.gameCompletedOverlay;
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
	 * Returns the entity manager
	 *
	 * @return entity manager
	 */
	public EntityManager getEntityManager() {
		return this.entityManager;
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
				this.game.getAudioPlayer().stopSoundTrack();
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
			this.game.getAudioPlayer().stopSoundTrack();
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
																	 .toList();
		for(Gangster g: gangsters) {
			if(this.player.getHitBox().intersects(g.getBullet().getHitBox())) {
				this.isDead = true;
				this.game.getAudioPlayer().stopSoundTrack();
				this.game.getAudioPlayer().playSoundEffects(AudioPlayer.GAMEOVER);
			}
		}
	}
	
	/**
	 * Checks if the player is on the final block of the level
	 * 
	 */
	private void checkPlayerPassed() {
		if(isFinal(this.player.getHitBox(), this.levelManager.getCurrentLevel().getMap())) {
			this.levelManager.levelPassed();
			if(this.isCompleted) this.game.getAudioPlayer().playSoundTrack(AudioPlayer.COMPLETED);
			else{
				this.entityManager.setCurrentEntities();
				this.resetPlaying();
			}
		}
	}
	
	/**
	 * resets the playing state
	 */
	public void resetPlaying() {
		this.isPaused = false;
		this.player = new Player();
		this.player.setCurrentLevel(levelManager.getCurrentLevel());
		this.levelManager.resetLabel();
		this.entityManager.resetObjects();
	}
	
	/**
	 * Restarts the playing state
	 */
	public void restartPlaying() {
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
		if(!isCompleted) {
			if(!isDead) {
				if(!isPaused) {
					this.checkPlayerPassed();
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
		}else {
			this.gameCompletedOverlay.update();
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
		if(this.isCompleted) this.gameCompletedOverlay.draw(g);
		else if(this.isDead) this.gameOverOverlay.draw(g);
		else if(this.isPaused) this.pauseOverlay.draw(g);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A -> this.player.setLeft(true);
			case KeyEvent.VK_D -> this.player.setRight(true);
			case KeyEvent.VK_SPACE -> this.player.setJump(true);
			case KeyEvent.VK_ESCAPE -> this.isPaused = !this.isPaused;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A -> this.player.setLeft(false);
			case KeyEvent.VK_D -> this.player.setRight(false);
			case KeyEvent.VK_SPACE -> this.player.setJump(false);
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

