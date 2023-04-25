package gamestates;

/**
 * Different states game can be in
 *
 */
public enum Gamestate {
	
	MENU, PLAYING, PAUSE, QUIT;
	
	public static Gamestate gameState = MENU;
}
