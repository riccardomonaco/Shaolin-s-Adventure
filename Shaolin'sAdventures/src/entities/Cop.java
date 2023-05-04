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
 * This represent an extension of {@link entities.Entity}
 * 
 * This is a type of entity, specifically a Cop, an enemy
 * 
 */
public class Cop extends Entity{
	
	private final float copSpeed;
	private final BufferedImage[] copAnimations;
	private boolean direction;
	private int tickDirection;

	/**
	 * Builds a new Cop, initializing his initial position
	 * 
	 * 
	 * @param initXPosition
	 * 		initial x position
	 * @param initYPosition
	 * 		initial y position
	 */
	public Cop(int initXPosition, int initYPosition) {
		super(initXPosition, initYPosition);
		this.initHitBox(HITBOX_W_OFFSET, HITBOX_H_OFFSET, HITBOX_X_OFFSET, HITBOX_Y_OFFSET);
		this.copAnimations = LoadSave.getEnemySprites().get(1);
		this.copSpeed = 1f;
		this.direction = true;
		this.tickDirection = 0;
	}
	
	/**
	 * Switches between the Cop sprites to create the animation
	 */
	private void updateAnimation() {
		this.tickAnimation++;
		if(this.tickAnimation >= this.speedAnimation) {
			this.indexAnimation++;
			if(this.indexAnimation >= this.copAnimations.length) {
				this.indexAnimation = 0;
			}
			this.tickAnimation = 0;
		}
	}
	
	/**
	 * Updates the position of the Cop in order
	 * to make it walk forward and backwards
	 */
	private void updatePosition() {
		if(this.direction) this.xPosition += this.copSpeed;
		else this.xPosition -= this.copSpeed;
		this.tickDirection++;
		if(this.tickDirection == 50) {
			this.tickDirection = 0;
			this.direction = !this.direction;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(int offset) {
		this.updatePosition();
		this.updateHitBox(HITBOX_X_OFFSET, HITBOX_Y_OFFSET);
		this.updateAnimation();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(Graphics2D g, int offset) {
		g.drawImage(copAnimations[this.indexAnimation], (int)xPosition - offset, (int)yPosition, TILE_SIZE, TILE_SIZE, null);
	}
}