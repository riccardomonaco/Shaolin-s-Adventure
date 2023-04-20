package entities;
import static utils.Constants.PanelConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.HITBOX_H_OFFSET;
import static utils.Constants.PlayerConstants.HITBOX_W_OFFSET;
import static utils.Constants.PlayerConstants.HITBOX_X_OFFSET;
import static utils.Constants.PlayerConstants.HITBOX_Y_OFFSET;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import utils.LoadSave;

public class Gangster extends Entity{
	
	private int tickAttack;
	private BufferedImage[] gangsterAnimations;
	
	public Gangster(int initXPosition, int initYPosition) {
		
		super(initXPosition, initYPosition);
		
		this.initHitBox(HITBOX_W_OFFSET, HITBOX_H_OFFSET, HITBOX_X_OFFSET, HITBOX_Y_OFFSET);
		this.gangsterAnimations = LoadSave.getEnemySprites().get(0);
				
		//initializing animations support counters
		this.indexAnimation = 0;
		this.tickAnimation = 0;
		this.speedAnimation = 40;
	}
	
	private void updateAnimation() {

		this.tickAnimation++;
		if(this.tickAnimation >= this.speedAnimation) {
			this.indexAnimation++;
			if(this.indexAnimation >= this.gangsterAnimations.length) {
				this.indexAnimation = 0;
			}
			this.tickAnimation = 0;
		}
	}
	
	@Override
	public void update() {
		this.updateAnimation();

	}
	
	@Override
	public void draw(Graphics2D g, int offset) {
		
		g.drawImage(gangsterAnimations[this.indexAnimation], (int)this.xPosition - offset, (int)this.yPosition, TILE_SIZE, TILE_SIZE, null);
		//System.out.println(xPosition+" "+yPosition);
	}
}
