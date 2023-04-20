package levels;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import entities.EntityManager;
import main.Game;
import utils.LoadSave;

import static utils.Constants.PanelConstants.*;
import static utils.Constants.LevelConstants.*;

public class LevelManager {

	private Game game;
	private Level currentLevel;
	private Level levelOne;
	private Level levelTwo;
	private Level levelThree;
	private EntityManager entityManager;
	
	private HashMap<Integer, BufferedImage> levelSprites;
	
	public LevelManager(Game game) {
		
		this.game = game;
		
		this.levelSprites = LoadSave.getLevelSprites();
		this.levelOne = new Level("/maps/levelonemap.txt", "/maps/leveloneentities.txt");
		this.levelTwo = new Level("/maps/leveltwomap.txt", "/maps/leveltwoentities.txt");
		this.levelThree = new Level("/maps/levelthreemap.txt", "/maps/levelthreeentities.txt");
		this.currentLevel = levelThree;
	}
	
	public Level getCurrentLevel() {
		
		return this.currentLevel;
	}
	
	public void update() {
		
	}
	
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
