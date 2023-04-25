package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import utils.LoadSave;

import static utils.Constants.PanelConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.HITBOX_H_OFFSET;
import static utils.Constants.PlayerConstants.HITBOX_W_OFFSET;
import static utils.Constants.PlayerConstants.HITBOX_X_OFFSET;
import static utils.Constants.PlayerConstants.HITBOX_Y_OFFSET;

/**
 * This represent an extension of {@link src.entities.Entity}
 * 
 * This is a type of entity, specifically a Gangster, an enemy
 * 
 */
public class Gangster extends Entity{
	
	private int tickAttack;
	private final BufferedImage[] gangsterAnimations;

	/**
	 * Builds a new Gangster, initializing his initial position
	 * 
	 * 
	 * @param initXPosition
	 * 		initial x position
	 * @param initYPosition
	 * 		initial y position
	 */
	public Gangster(int initXPosition, int initYPosition) {
		super(initXPosition, initYPosition);
		this.initHitBox(HITBOX_W_OFFSET, HITBOX_H_OFFSET, HITBOX_X_OFFSET, HITBOX_Y_OFFSET);
		this.gangsterAnimations = LoadSave.getEnemySprites().get(0);
	}
	
	/**
	 * Switches between the Gangster sprites to create the animation
	 */
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
		this.updateAnimation();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(Graphics2D g, int offset) {
		g.drawImage(gangsterAnimations[this.indexAnimation], (int)this.xPosition - offset, (int)this.yPosition, TILE_SIZE, TILE_SIZE, null);
	}
}
