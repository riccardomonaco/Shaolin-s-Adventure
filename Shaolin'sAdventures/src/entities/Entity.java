package entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import static utils.Constants.PanelConstants.*;

/**
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
	 * 		initial x position
	 * @param y
	 * 		initial y position
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
	 * sets the hitbox relatively at the offset of the entity
	 * 
	 * @param wOffset
	 * 		hitbox width offset
	 * @param hOffset
	 * 		hitbox height offset
	 * @param xOffset
	 * 		hitbox x offset
	 * @param yOffset
	 * 		hitbox y offset
	 */
	protected void initHitBox(int wOffset, int hOffset, int xOffset, int yOffset) {
		this.hitBox = new Rectangle2D.Float(xPosition + xOffset, 
											yPosition + yOffset, 
											TILE_SIZE - wOffset, 
											TILE_SIZE - hOffset);
	}
	
	/**
	 * updates the position of the hitbox applying the offsets
	 * 
	 * @param xOffset
	 * 		hitbox x offset
	 * @param yOffset
	 * 		hitbox y offset
	 */
	protected void updateHitBox(int xOffset, int yOffset) {
		this.hitBox.x = (xPosition + xOffset);
		this.hitBox.y = (yPosition + yOffset);
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
	 * 		java drawing object
	 * @param offset
	 * 		current offset
	 */
	public void draw(Graphics2D g, int offset) {}
	
	/**
	 * Updates the state of the entity
	 */
	public void update(int offset) {}
	
}

