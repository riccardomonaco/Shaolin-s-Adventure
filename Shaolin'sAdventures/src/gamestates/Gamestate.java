package gamestates;

/**
 * This enum represents the different states that game can be in
 *
 */
public enum Gamestate {
	
	MENU, PLAYING, PAUSE, QUIT;
	
	public static Gamestate gameState = MENU;
}
