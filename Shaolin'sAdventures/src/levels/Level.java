package levels;

import static utils.Constants.PanelConstants.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import entities.Entity;

import static utils.Constants.LevelConstants.*;

import utils.LoadSave;

/**
 * This represents a generic level of the game
 * 
 */
public class Level {
	
	private int[][] map;
	private List<Entity> enemies;
	private BufferedImage levelOverlay;
	
	/**
	 * Builds a new level loading the map
	 * and the correlated enemies
	 * 
	 * @param mPath
	 * 		map file path
	 * @param ePath
	 * 		enemies map file path
	 * @param imgPath
	 * 		level overlay file path
	 */
	public Level(String mPath, String ePath, String imgPath) {
		this.map = LoadSave.loadMap(mPath);
		this.enemies = LoadSave.loadEnemies(ePath);
		this.levelOverlay = LoadSave.getImage(imgPath);
	}
	
	/**
	 * Builds a new level based on an int matrix
	 * which represents the map
	 * 
	 * @param map
	 * 		int matrix
	 */
	public Level(int[][] map) {
		this.map = map;
	}
	
	/**
	 * Return the map as int matrix
	 * 
	 * @return map
	 */
	public int[][] getMap(){
		return this.map;
	}
	
	/**
	 * Returns the enemies list
	 * 
	 * @return enemies
	 */
	public List<Entity> getEnemies(){
		return this.enemies;
	}
	
	/**
	 * Draws the level label
	 * 
	 * @param g
	 * 		java drawing object
	 */
	public void drawOverlay(Graphics2D g) {
		g.drawImage(levelOverlay, LVL_OVERLAY_X, LVL_OVERLAY_Y, LVL_OVERLAY_W, LVL_OVERLAY_H, null);
	}
	
	/**
	 * Returns the actual window with map tiles
	 * 
	 * @param offset
	 * 		offset for the beginning and end of map drawing
	 * @return int matrix
	 */
	public int[][] getWindow(int offset) {
		int col = 0, row = 0;
		int maxOffset = MAP_COL;
		int[][] window = new int[SCREEN_COL][SCREEN_ROW];
		if(col + offset > maxOffset) offset = maxOffset;
		while(col < SCREEN_COL && row < SCREEN_ROW) {
			while(col < SCREEN_COL) {
				window[col][row] = map[col + offset][row];
				col++;
			}
			if(col == SCREEN_COL) {
				col = 0;
				row++;
			}			
		}
		return window;
	}
}
