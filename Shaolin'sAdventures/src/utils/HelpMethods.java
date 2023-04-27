package utils;

import static utils.Constants.PanelConstants.*;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.security.PublicKey;

import utils.Constants.PanelConstants;
import utils.Constants.PlayerConstants;

import static utils.Constants.LevelConstants.*;

/**
 * This represents a class containing generic helping methods
 * 
 */
public class HelpMethods {
	
	/**
	 * Checks if the position (the next player position) 
	 * is touching objects or edges
	 */
	public static boolean canMoveHere(float x, float y, float width, float height, int[][] map) {
		if(!isSolid(x, y, map))								//left-top corner
			if(!isSolid(x + width, y, map))					//right-top corner
				if(!isSolid(x + width, y + height, map))	//right-bottom corner
					if(!isSolid(x, y + height, map))		//left-bottom corner
						return true;
		return false;
	}
	
	/**
	 * Checks if the material placed in a given position
	 * is a solid one
	 */
	private static boolean isSolid(float x, float y, int[][] map) {
		if(x < 0 || x >= MAP_WIDTH) return true;
		if(y < 0 || y >= SCREEN_HEIGHT) return true;
		float xIndex = x / TILE_SIZE;
		float yIndex = y / TILE_SIZE;
		int material = map[(int)xIndex][(int)yIndex];
		switch (material) {
			case WALL:
				return true;
			case RIGHT_CORNER:
				return true;
			case LEFT_CORNER:
				return true;
			case ROOF:
				return true;
			case BACKGROUND:
				return false;
			default:
				return true;
		}
	}
	
	/**
	 * Checks if an entity is on top of a solid tile
	 * 
	 * @param hitbox
	 * 		entity hitbox
	 * @param map
	 * 		current level map
	 * @return boolean 
	 */
	public static boolean isOnTheFloor(Rectangle2D.Float hitbox, int[][] map) {
		if(!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, map))						//left-bottom corner
			if(!isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, map))	//right-bottom corner
				return false;
		return true;
	}
	
	/**
	 * Checks if the player is fell of the roof
	 * 
	 * @param hitbox
	 * 		player hitbox
	 * @return boolean
	 */
	public static boolean isFellOff(Rectangle2D.Float hitbox) {
		return (hitbox.y + hitbox.height + PlayerConstants.HITBOX_W_OFFSET) >= PanelConstants.SCREEN_HEIGHT;
	}
}
