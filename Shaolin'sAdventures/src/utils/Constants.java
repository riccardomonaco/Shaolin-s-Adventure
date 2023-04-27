package utils;

/**
 * This represents a class containing
 * all the constants for the game
 *
 */
public class Constants {
	
	public static class PanelConstants{
		public static final int O_TILE_SIZE = 32;
		public static final int SCALE = 2;
		public static final int TILE_SIZE = O_TILE_SIZE * SCALE;		//96x96
		
		public static final int SCREEN_COL = 16;
		public static final int SCREEN_ROW = 9;
		public static final int SCREEN_WIDTH = TILE_SIZE * SCREEN_COL;	//1536px
		public static final int SCREEN_HEIGHT = TILE_SIZE * SCREEN_ROW;	//864px
	}

	public static class PlayerConstants{
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int JUMPING = 2;
		
		public static final int INITPLAYERX = PanelConstants.TILE_SIZE;
		public static final int INITPLAYERY = PanelConstants.TILE_SIZE * 2;
		
		public static final int HITBOX_W_OFFSET = 12 * PanelConstants.SCALE;
		public static final int HITBOX_H_OFFSET = 8 * PanelConstants.SCALE;
		public static final int HITBOX_X_OFFSET = 8 * PanelConstants.SCALE;
		public static final int HITBOX_Y_OFFSET = 5 * PanelConstants.SCALE;
	}
	
	public static class LevelConstants{
		public static final int WALL = 0;
		public static final int RIGHT_CORNER = 1;
		public static final int LEFT_CORNER = 2;
		public static final int ROOF = 3;
		public static final int BACKGROUND = 4;
		
		public static final int MAP_COL = PanelConstants.SCREEN_COL * 3;
		public static final int MAP_ROW = PanelConstants.SCREEN_ROW;
		public static final int MAP_WIDTH = PanelConstants.TILE_SIZE * MAP_COL;
		public static final int MAP_HEIGTH = PanelConstants.TILE_SIZE * MAP_ROW;

	}
	
	public static class EnemiesConstants{
		public static class GangsterConstants{
			public static final int GANGSTERX = PanelConstants.TILE_SIZE * 2;
			public static final int GANGSTERY = PanelConstants.TILE_SIZE * 2;
			
			public static final int HITBOX_W_OFFSET = 12 * PanelConstants.SCALE;
			public static final int HITBOX_H_OFFSET = 8 * PanelConstants.SCALE;
			public static final int HITBOX_X_OFFSET = 8 * PanelConstants.SCALE;
			public static final int HITBOX_Y_OFFSET = 5 * PanelConstants.SCALE;
		}
	}
	
	public static class UI{
		public static final int BG_WIDTH = PanelConstants.SCREEN_WIDTH;
		public static final int BG_HEIGHT = PanelConstants.SCREEN_HEIGHT;
		public static final int SPLASH_WIDTH = 128;
		
		public static class ButtonsConstants{
			public static final int MENU_WIDTH = 128 * PanelConstants.SCALE;
			public static final int MENU_HEIGHT = 128 * PanelConstants.SCALE;
			public static final int PAUSE_WIDTH = 64 * PanelConstants.SCALE;
			public static final int PAUSE_HEIGHT = 64 * PanelConstants.SCALE;
			public static final int PLAY_OFFSET = -275;
			public static final int QUIT_OFFSET = -10;
			public static final int HOME_BT_X = (int)PanelConstants.SCREEN_WIDTH/2 - 128;
			public static final int HOME_BT_Y = (int)PanelConstants.SCREEN_HEIGHT/2 - PanelConstants.TILE_SIZE*3;
			public static final int RESTART_BT_X = (int)PanelConstants.SCREEN_WIDTH/2;
			public static final int RESTART_BT_Y = (int)PanelConstants.SCREEN_HEIGHT/2 - PanelConstants.TILE_SIZE*3;
			public static final int VOLUME_BT_X = (int)PanelConstants.SCREEN_WIDTH/2 - 64;
			public static final int VOLUME_BT_Y = (int)PanelConstants.SCREEN_HEIGHT/2 - PanelConstants.TILE_SIZE;
		}
	}
	
	
}
