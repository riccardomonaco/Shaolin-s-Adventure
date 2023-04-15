package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame jFrame;

	public GameWindow(GamePanel gamePanel) {
		this.jFrame = new JFrame();
		this.jFrame.setTitle("Shao's Adventure");
		this.jFrame.setResizable(false);
		this.jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.jFrame.add(gamePanel);
		this.jFrame.pack();
		this.jFrame.setLocationRelativeTo(null);
		this.jFrame.setVisible(true);
		this.jFrame.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				gamePanel.getGame().getPlaying().getPlayer().resetDirections();
				
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				
			}
		});
	}
}