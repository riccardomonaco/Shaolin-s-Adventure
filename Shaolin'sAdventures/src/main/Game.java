package main;

import java.awt.Graphics2D;

import audio.AudioPlayer;
import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;

public class Game implements Runnable{
	
	private final int FPS = 60;	// frames per second
	private final int UPS = 150; // updates per second
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	
	private Playing playingState;
	private Menu menuState;
	private AudioPlayer audioPlayer;
	
	public Game() {
		
		this.playingState = new Playing(this);
		this.menuState = new Menu(this);
		this.audioPlayer = new AudioPlayer();
		
		this.gamePanel = new GamePanel(this);
		this.gameWindow = new GameWindow(gamePanel);
		
		this.startGameThread();
		
		this.gamePanel.requestFocus();
	}
	
	private void startGameThread() {
		
		this.gameThread = new Thread(this);
		this.gameThread.start();
	}
	
	public void update() {
		
		switch(Gamestate.gameState) {
		case MENU:
			menuState.update();
			break;
		case PLAYING:
			playingState.update();
			break;
		case QUIT:
			System.exit(0);
			break;
		default:
			break;
		}
	}
	
	public void render(Graphics2D g) {
		
		switch (Gamestate.gameState) {
		case MENU:
			menuState.draw(g);
			break;
		case PLAYING:
			playingState.draw(g);
			break;
		default:
			break;		
		}
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; //drawing time, 0.01666
		double updateInterval = 1000000000/UPS; //drawing time, 0.???? 
		double deltaDraw = 0;
		double deltaUpdate = 0;
		
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			deltaDraw += (currentTime-lastTime)/drawInterval;
			deltaUpdate += (currentTime-lastTime)/updateInterval;
			lastTime = currentTime;
			
			//updating the entities/environment 150 times per second
			if(deltaUpdate >= 1) {
				this.gamePanel.update();
				deltaUpdate--;
			}
			
			//drawing the frame 60 times per second
			if(deltaDraw >= 1) {
				this.gamePanel.repaint();
				deltaDraw--;
			}
		}
	}
	
	public Playing getPlaying() {
		
		return this.playingState;
	}
	
	public Menu getMenu() {
		
		return this.menuState;
	}
	
	public AudioPlayer getAudioPlayer() {
		
		return this.audioPlayer;
	}
}
