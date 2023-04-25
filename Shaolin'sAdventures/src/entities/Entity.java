package entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import static utils.Constants.PanelConstants.*;

/**
 * 
 * Represents the generic entity of the game
 * 
 */
public class Entity {

	protected float xPosition;
	protected float yPosition;
	protected Rectangle2D.Float hitBox;
	
	protected int tickAnimation;
	protected int indexAnimation;
	protected int speedAnimation;
	
	/**
	 * Builds and Entity defining his initial position
	 * 
	 * @param x
	 * 			initial x position
	 * @param y
	 * 			initial y position
	 */
	public Entity(float x, float y) {
		this.xPosition = x;
		this.yPosition = y;
		this.setDefaults();
	}
	
	/**
	 * initializes the entity default values
	 */
	public void setDefaults() {
		this.indexAnimation = 0;
		this.tickAnimation = 0;
		this.speedAnimation = 35;
	}
	
	/**
	 * sets the hitbox relative to the offset of the entity
	 * 
	 * @param HITBOX_W_OFFSET
	 * 					hitbox width offset
	 * @param HITBOX_H_OFFSET
	 * 					hitbox height offset
	 * @param HITBOX_X_OFFSET
	 * 					hitbox x offset
	 * @param HITBOX_Y_OFFSET
	 * 					hitbox y offset
	 */
	protected void initHitBox(int HITBOX_W_OFFSET, int HITBOX_H_OFFSET, int HITBOX_X_OFFSET, int HITBOX_Y_OFFSET) {
		this.hitBox = new Rectangle2D.Float(xPosition + HITBOX_X_OFFSET, 
											yPosition + HITBOX_Y_OFFSET, 
											TILE_SIZE - HITBOX_W_OFFSET, 
											TILE_SIZE - HITBOX_H_OFFSET);
	}
	
	/**
	 * updates the position of the hitbox applying the offsets
	 * 
	 * @param HITBOX_X_OFFSET
	 * 					hitbox x offset
	 * @param HITBOX_Y_OFFSET
	 * 					hitbox y offset
	 */
	protected void updateHitBox(int HITBOX_X_OFFSET, int HITBOX_Y_OFFSET) {
		this.hitBox.x = (xPosition + HITBOX_X_OFFSET);
		this.hitBox.y = (yPosition + HITBOX_Y_OFFSET);
	}
	
    /**
     * Returns the entity's hitbox
     * 
     * @return the hitbox of this entity
     */
	public Rectangle2D.Float getHitBox() {	
		return this.hitBox;
	}
	
	/**
	 * Draws the current sprite at the current coordinates
	 * 
	 * @param g
	 * 			java drawing object
	 * @param offset
	 * 			current offset
	 */
	public void draw(Graphics2D g, int offset) {}
	
	/**
	 * Updates the state of the entity
	 */
	public void update() {}
	
}

