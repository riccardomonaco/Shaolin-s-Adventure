package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;
/**
 * This represents the main game window in which 
 * the game panel is displayed
 *
 */
public class GameWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JFrame jFrame;

	/**
	 * Builds a new game window 
	 * 
	 * @param game panel
	 */
	public GameWindow(GamePanel gamePanel) {
		this.jFrame = new JFrame();
		this.jFrame.setTitle("Shao's Adventure");
		this.jFrame.setResizable(false);
		this.jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.jFrame.add(gamePanel);
		this.jFrame.pack();
		this.jFrame.setLocationRelativeTo(null);
		this.jFrame.setVisible(true);
		/*
		 * Checks the events of the main window
		 */
		this.jFrame.addWindowFocusListener(new WindowFocusListener() {
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void windowLostFocus(WindowEvent e) {
				gamePanel.getGame().getPlaying().getPlayer().resetDirections();
				gamePanel.getGame().getPlaying().isPaused = true;
			}
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void windowGainedFocus(WindowEvent e) {}
		});
	}
}