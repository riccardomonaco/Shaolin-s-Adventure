package levels;

import static utils.Constants.PanelConstants.*;

import java.util.List;

import entities.Entity;

import static utils.Constants.LevelConstants.*;

import utils.LoadSave;

public class Level {
	
	private int[][] map;
	private List<Entity> enemies;
	
	public Level(String mPath, String ePath) {
		
		this.map = LoadSave.loadMap(mPath);
		this.enemies = LoadSave.loadEnemies(ePath);
	}
	
	public Level(int[][] map) {
		
		this.map = map;
	}
	
	public int[][] getMap(){
		
		return this.map;
	}
	
	public List<Entity> getEnemies(){
		
		return this.enemies;
	}
	
	public int[][] getWindow(int offset) {
		
		int col = 0, row = 0;
		int maxOffset = MAP_COL;
		
		System.out.println(maxOffset);
		System.out.println(offset);
		
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
