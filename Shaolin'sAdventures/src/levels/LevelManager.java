package levels;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import entities.EntityManager;
import main.Game;
import utils.LoadSave;

import static utils.Constants.PanelConstants.*;
import static utils.Constants.LevelConstants.*;

/**
 * This represent a level manager which contains 
 * and manages all the levels
 * 
 *
 */
public class LevelManager {

	private Game game;
	
	private Level currentLevel;
	private Level[] levels;
	private int levelIndex;
	
	private EntityManager entityManager;
	private HashMap<Integer, BufferedImage> levelSprites;
	
	/**
	 * Builds a new level manager
	 * 
	 * @param game
	 */
	public LevelManager(Game game) {
		this.game = game;
		this.levelSprites = LoadSave.getLevelSprites();
		this.levelIndex = 0;
		this.levels = new Level[3];
		this.levels[0] = new Level("/maps/levelonemap.txt", "/maps/leveloneentities.txt");
		this.levels[1] = new Level("/maps/leveltwomap.txt", "/maps/leveltwoentities.txt");
		this.levels[2] = new Level("/maps/levelthreemap.txt", "/maps/levelthreeentities.txt");
		this.currentLevel = this.levels[this.levelIndex];
	}

	/**
	 * Returns the current level
	 * 
	 * @return current level
	 */
	public Level getCurrentLevel() {
		return this.currentLevel;
	}
	
	/**
	 * Goes to the next level
	 * 
	 */
	public void levelPassed() {
		this.levelIndex++;
		this.currentLevel = this.levels[this.levelIndex];
	}
	
	/**
	 * Reset the first level
	 * 
	 */
	public void resetLevels() {
		this.levelIndex = 0;
		this.currentLevel = this.levels[this.levelIndex];
	}
	
	/**
	 * 
	 */
	public void update() {
	}
	
	/**
	 * Draws the current level map tiles based
	 * on the current window offset
	 * 
	 * @param g
	 * 		java drawing object
	 * @param offset
	 * 		current offset
	 */
	public void draw (Graphics2D g, int offset) {
		int col = 0, row = 0, x = 0 , y = 0;
		while(col < MAP_COL && row < MAP_ROW) {
			g.drawImage(levelSprites.get(this.currentLevel.getMap()[col][row]), x - offset, y, TILE_SIZE, TILE_SIZE, null);
			col++;
			x+=TILE_SIZE;
			if(col == MAP_COL) {
				col = 0; x = 0 ;
				row++;
				y+=TILE_SIZE;			
			}
		}
	}
}
