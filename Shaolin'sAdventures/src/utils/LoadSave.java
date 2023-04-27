package utils;

import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

import javax.imageio.ImageIO;

import entities.Cop;
import entities.Entity;
import entities.Gangster;

import static utils.Constants.LevelConstants.*;
import static utils.Constants.PanelConstants.*;

/**
 * This represents a class containing methods to retrieve and store data
 *
 */
public class LoadSave {
	
	/**
	 * Retrieves player sprites related to his different actions
	 * 
	 * @return images array hashmap
	 */
	public static HashMap<Integer, BufferedImage[]> getPlayerSprites(){
		BufferedImage[] idle = new BufferedImage[1];
		BufferedImage[] running = new BufferedImage[2];
		BufferedImage[] jumping = new BufferedImage[6];
		HashMap<Integer, BufferedImage[]> playerAnimations = new HashMap<>();
		try {
			idle[0]	   = ImageIO.read(LoadSave.class.getResourceAsStream("/player/shao1.png"));
			running[0] = ImageIO.read(LoadSave.class.getResourceAsStream("/player/shao1.png"));
			running[1] = ImageIO.read(LoadSave.class.getResourceAsStream("/player/shao2.png"));
			jumping[0] = ImageIO.read(LoadSave.class.getResourceAsStream("/player/shaojump1.png"));
			jumping[1] = ImageIO.read(LoadSave.class.getResourceAsStream("/player/shaojump2.png"));
			jumping[2] = ImageIO.read(LoadSave.class.getResourceAsStream("/player/shaojump3.png"));
			jumping[3] = ImageIO.read(LoadSave.class.getResourceAsStream("/player/shaojump2.png"));
			jumping[4] = ImageIO.read(LoadSave.class.getResourceAsStream("/player/shaojump1.png"));
			jumping[5] = ImageIO.read(LoadSave.class.getResourceAsStream("/player/shao1.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		playerAnimations.put(0, idle);
		playerAnimations.put(1, running);
		playerAnimations.put(2, jumping);
		return playerAnimations;
	}
	
	/**
	 * Retrieves enemies sprites
	 * 
	 * @return images array hashmap
	 */
	public static HashMap<Integer, BufferedImage[]> getEnemySprites(){
		BufferedImage[] gangster = new BufferedImage[2];
		BufferedImage[] cop = new BufferedImage[2];
		HashMap<Integer, BufferedImage[]> enemyAnimations = new HashMap<>();
		try {
			gangster[0] = ImageIO.read(LoadSave.class.getResourceAsStream("/enemies/gangster1.png"));
			gangster[1] = ImageIO.read(LoadSave.class.getResourceAsStream("/enemies/gangster2.png"));
			cop[0] = ImageIO.read(LoadSave.class.getResourceAsStream("/enemies/cop1.png"));
			cop[1] = ImageIO.read(LoadSave.class.getResourceAsStream("/enemies/cop2.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		enemyAnimations.put(0, gangster);
		enemyAnimations.put(1, cop);
		return enemyAnimations;
	}
	
	/**
	 * Retrieves map blocks images
	 * 
	 * @return images array hashmap
	 */
	public static HashMap<Integer, BufferedImage> getLevelSprites(){
		BufferedImage wall = null;
		BufferedImage leftCorner = null;
		BufferedImage rightCorner = null;
		BufferedImage roof = null;
		BufferedImage background = null;
		BufferedImage wallStair = null;
		HashMap<Integer, BufferedImage> levelSprites = new HashMap<>();
		try {
			wall		= ImageIO.read(LoadSave.class.getResourceAsStream("/levels/wall.png"));
			leftCorner 	= ImageIO.read(LoadSave.class.getResourceAsStream("/levels/leftCorner.png"));
			rightCorner = ImageIO.read(LoadSave.class.getResourceAsStream("/levels/rightCorner.png"));
			roof 		= ImageIO.read(LoadSave.class.getResourceAsStream("/levels/roof.png"));
			background	= ImageIO.read(LoadSave.class.getResourceAsStream("/levels/background.png"));
			wallStair	= ImageIO.read(LoadSave.class.getResourceAsStream("/levels/wallStair.png"));	
		}catch(IOException e) {
			e.printStackTrace();
		}
		levelSprites.put(0, wall);
		levelSprites.put(1, leftCorner);
		levelSprites.put(2, rightCorner);
		levelSprites.put(3, roof);
		levelSprites.put(4, background);
		levelSprites.put(5, wallStair);
		return levelSprites;
	}
	
	/**
	 * Retrieves button images
	 * 
	 * @param imgPath
	 * 		image file paths
	 * @return images array
	 */
	public static BufferedImage[] getButtonsImages(String[] imgPaths) {
		BufferedImage[] img = new BufferedImage[imgPaths.length];
		int counter = 0;
		try {
			for(String path: imgPaths){
				img[counter] = ImageIO.read(LoadSave.class.getResourceAsStream(path));
				counter++;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	/**
	 * Retrieves a single image
	 * 
	 * @param imgPath
	 * 		image file path
	 * @return
	 */
	public static BufferedImage getImage(String imgPath) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(LoadSave.class.getResourceAsStream(imgPath));
		}catch(IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	/**
	 * Extracts map tiles data from text matrix
	 * with number references to the different materials
	 * 
	 * @param fPath
	 * 		map file path
	 * @return integer matrix
	 */
	public static int[][] loadMap(String fPath) {
		int[][] map = new int[MAP_COL][MAP_ROW];
		try {
			InputStream stream = LoadSave.class.getResourceAsStream(fPath);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(stream));
			int col = 0, row = 0;
			while(col < MAP_COL && row < MAP_ROW) {
				String line = bReader.readLine();
				while(col < MAP_COL) {
					String numbers[]= line.split(" ");
					map[col][row] = Integer.parseInt(numbers[col]);
					col++;
				}
				if(col == MAP_COL) { 
					col = 0;
					row++;
				}
			}		
			bReader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * Extracts enemies data from text matrix
	 * with number references to the different types 
	 * 
	 * @param fPath
	 * 		enemies map file path
	 * @return enemy list
	 */
	public static List<Entity> loadEnemies(String fPath) {
		List<Entity> enemies = new ArrayList<>();
		int[][] map = loadMap(fPath);
		int col = 0, row = 0;
		while(col < MAP_COL && row < MAP_ROW) {
			while(col < MAP_COL) {
				switch (map[col][row]) {
					case 5:
						enemies.add(new Gangster(col * TILE_SIZE, row * TILE_SIZE));
						break;
					case 6:
						enemies.add(new Cop(col * TILE_SIZE, row * TILE_SIZE));
						break;
				}
				col++;
			}
			if(col == MAP_COL) { 
				col = 0;
				row++;
			}
		}
		return enemies;
	}
}
