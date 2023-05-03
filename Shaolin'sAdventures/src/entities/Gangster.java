package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import objects.Bullet;
import utils.LoadSave;
import utils.Constants.PanelConstants;

import static utils.Constants.PanelConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.ObjectsConstants.*;

/**
 * This represent an extension of {@link src.entities.Entity}
 * 
 * This is a type of entity, specifically a Gangster, an enemy
 * 
 */
public class Gangster extends Entity{
	
	private int tickAttack;
	private int speedAttack;
	
	private Bullet bullet;
	
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
		this.setAttackDefaults();
		this.gangsterAnimations = LoadSave.getEnemySprites().get(0);
	}
	
	/**
	 * Sets the variables linked to the attacking system
	 * 
	 */
	public void setAttackDefaults() {
		this.tickAttack = 0;
		this.speedAttack = 35;
		this.bullet = new Bullet(this.getHitBox().x - BULLET_X_INIT, this.getHitBox().y + BULLET_Y_INIT, BULLET_W, BULLET_H, "/objects/bullet.png");
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
	
	private void updateAttack() {
		this.tickAttack++;
		if(this.tickAttack >= this.speedAttack) {
			updateAnimation();
			this.bullet.activate();
			this.tickAttack = 0;
		}
	}
	
	/**
	 * Returns the bullet
	 * 
	 * @return bullet
	 */
	public Bullet getBullet() {
		return this.bullet;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(int offset) {
		if(this.xPosition < PanelConstants.SCREEN_WIDTH + offset) {
			this.updateAttack();
			if(this.bullet.getStatus()) this.bullet.update();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(Graphics2D g, int offset) {
		g.drawImage(gangsterAnimations[this.indexAnimation], (int)this.xPosition - offset, (int)this.yPosition, TILE_SIZE, TILE_SIZE, null);
		if(this.bullet.getStatus())	this.bullet.draw(g, offset);
	}
}
