package levels;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entities.EntityManager;
import main.Game;
import utils.LoadSave;

import static utils.Constants.PanelConstants.*;
import static utils.Constants.LevelConstants.*;

/**
 * This represents a level manager which contains
 * and manages all the levels
 *
 */
public class LevelManager {

	private final Game game;
	private final List<Level> levels;
	private int levelIndex;
	private int levelLabel;
	private final HashMap<Integer, BufferedImage> levelSprites;
	
	/**
	 * Builds a new level manager
	 * 
	 * @param game
	 */
	public LevelManager(Game game) {
		this.game = game;
		this.levelSprites = LoadSave.getLevelSprites();
		this.levelIndex = 0;
		this.levelLabel = 300;
		this.levels = new ArrayList<>(3);
		this.initLevels();
	}

	/**
	 * Initializes the existing levels
	 *
	 */
	private void initLevels(){
		this.levels.add(
				new Level("/maps/levelonemap.txt",
						"/maps/leveloneentities.txt",
						"/overlays/level1.png")
		);
		this.levels.add(
				new Level("/maps/leveltwomap.txt",
						"/maps/leveltwoentities.txt",
						"/overlays/level2.png")
		);
		this.levels.add(
				new Level("/maps/levelthreemap.txt",
						"/maps/levelthreeentities.txt",
						"/overlays/level3.png")
		);
	}

	/**
	 * Returns the current level
	 * 
	 * @return current level
	 */
	public Level getCurrentLevel() {
		return this.levels.get(levelIndex);
	}
	
	/**
	 * Goes to the next level
	 * 
	 */
	public void levelPassed() {
		this.levelIndex++;
		if(this.checkGameCompleted()) this.game.getPlaying().isCompleted = true;
		else this.resetLabel();
	}
	
	/**
	 * Reset the first level
	 * 
	 */
	public void resetLevels() {
		this.levelIndex = 0;
		this.levelLabel = 300;
	}
	
	/**
	 * Resets the level label value
	 * 
	 */
	public void resetLabel() {
		this.levelLabel = 300;
	}
	
	/**
	 * Returns if the currently completed level was the
	 * last one
	 * 
	 * @return boolean
	 */
	public boolean checkGameCompleted() {
		return this.levelIndex >= this.levels.toArray().length;
	}
	
	/**
	 * Updates the level manager
	 * 
	 */
	public void update() {
		this.levelLabel--;
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
			g.drawImage(levelSprites.get(this.levels.get(levelIndex).getMap()[col][row]), x - offset, y, TILE_SIZE, TILE_SIZE, null);
			col++;
			x+=TILE_SIZE;
			if(col == MAP_COL) {
				col = 0; x = 0 ;
				row++;
				y+=TILE_SIZE;			
			}
		}
		if(levelLabel > 0) {
			this.levels.get(levelIndex).drawOverlay(g);
		}
	}
}
