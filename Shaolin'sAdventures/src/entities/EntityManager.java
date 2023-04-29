package entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import levels.LevelManager;
import main.Game;

/**
 * Represents the class which manages the current entities
 * relative to the current level
 * 
 */
public class EntityManager {
	
	private final Game game;
	private final LevelManager levelManager;
	public List<Entity> currentEntities;
	
	/**
	 * Builds a new EntityManager, defining the LevelManager
	 * and the Game
	 * 
	 * @param game
	 * 		reference at the current Game class
	 * @param levelManager
	 * 		reference at the LevelManager
	 */
	public EntityManager(Game game, LevelManager levelManager) {	
		this.game = game;
		this.levelManager = levelManager;
		this.currentEntities = new ArrayList<>();
		this.setCurrentEntities();
	}

	/**
	 * retrieves the current entities from the LevelManager
	 */
	public void setCurrentEntities() {
		this.currentEntities = this.levelManager.getCurrentLevel().getEnemies();
	}
		
	/**
	 * Updates every entity currently existing
	 */
	public void update() {
		for(Entity e: currentEntities) {
			e.update(this.game.getPlaying().getCurrentOffset());
		}
	}
	
	/**
	 * Draws every entity currently existing
	 * 
	 * @param g
	 * 		java drawing object
	 * @param offset
	 * 		current offset
	 */
	public void draw (Graphics2D g, int offset) {	
		for(Entity e: currentEntities) {
			e.draw(g, offset);
		}
	}
}
