package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import inputs.KeysInputManager;
import inputs.MouseInputManager;

import static utils.Constants.PanelConstants.*;

/**
 * Represents the main ui game panel
 * 
 */
public class GamePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private KeysInputManager keysInputManager;
	private MouseInputManager mouseInputManager;
	private Game game;

	/**
	 * Builds a new game panel 
	 * 
	 * @param game
	 */
	public GamePanel(Game game) {
		this.game = game;
		this.keysInputManager = new KeysInputManager(this);
		this.mouseInputManager = new MouseInputManager(this);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.gray);
		this.setDoubleBuffered(true);
		this.addKeyListener(keysInputManager);
		this.addMouseListener(mouseInputManager);
		this.setFocusable(true);
	}
	
	/**
	 * Returns the game object
	 * 
	 * @return game
	 */
	public Game getGame() {
		return this.game;
	}
	
	/**
	 * Updates the game and his elements
	 * 
	 */
	public void update() {
		game.update();
	}
	
	/**
	 * Draws the panel and the game objects 
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render((Graphics2D)g);
		g.dispose();
	}
}
